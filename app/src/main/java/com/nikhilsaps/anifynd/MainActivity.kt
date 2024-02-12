package com.nikhilsaps.anifynd

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.nikhilsaps.anifynd.databinding.ActivityMainBinding
import com.nikhilsaps.anifynd.fragments.AnimeDBFragment
import com.nikhilsaps.anifynd.fragments.HomeFragment
import com.nikhilsaps.anifynd.fragments.ListFragment
import com.nikhilsaps.anifynd.fragments.ReadDBFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val PREFS_NAME = "MyPrefsFile"
    private val FIRST_TIME_KEY = "firstTime"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val firstTime = sharedPref.getBoolean(FIRST_TIME_KEY, true)
            //checking if the app is launched 1st time
        if (firstTime) {
            // The app is opened for the first time
            // Launch the activity you want to run on the first opening
            val intent = Intent(this, OneTimeActivity::class.java)
            startActivity(intent)
            finish()
            // Set the flag to false in shared preferences
            with(sharedPref.edit()) {
                putBoolean(FIRST_TIME_KEY, false)
                apply()
            }
        } else {
            // The app has been opened before
            //run this once  to  make  the home fragment appear  on launch
            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainFragContainer.id,HomeFragment())
                addToBackStack(null)
                commit()
            }
            // Continue with the normal flow or launch another activity if needed
        }
        binding.mainBtmNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,HomeFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.list_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,ListFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.anime_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,AnimeDBFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.read_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,ReadDBFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                else -> false

            }
        }
    }

    override fun onStart() {
        super.onStart()
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentUser=sharedPref.getString("currentUser","").toString()
        binding.currentuserimg.text=currentUser

        fetchmangadata()


    }
    private fun fetchmangadata() {
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val db = Firebase.firestore
        var MangaList:ArrayList<MangaData> = ArrayList()

        db.collection("MangaDB")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var manga = MangaData(document.data["name"].toString(),document.data["mcname"].toString(),document.data["imgsrc"].toString(),document.data["assi_read"].toString(), document.data["nik_read"].toString(),document.data["desc"].toString())
                    MangaList.add(manga)
                    // Log.d("TAG", "${document.id} => ${document.data}, ${MangaList[0]}")
                }
                Log.d("TAG", " ${MangaList[0]}")
                val gson = Gson()
                val json = gson.toJson(MangaList)
                editor.putString("KEY_MANGA_LIST", json).apply()
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }
}