package com.nikhilsaps.anifynd

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
    private lateinit var MangaList:ArrayList<MangaData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d("TAG", "MainActivity :OnCreate()")

        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val firstTime = sharedPref.getBoolean(FIRST_TIME_KEY, true)
            //checking if the app is launched 1st time
        if (firstTime) {
            // The app is opened for the first time
            val intent = Intent(this, OneTimeActivity::class.java)
            startActivity(intent)
            finish()
            // Set the flag to false in shared preferences
            with(sharedPref.edit()) {
                putBoolean(FIRST_TIME_KEY, false)
                apply()
            }
        } else {
            /*This means its not first time launched hence set  home fragment as  the  main
            entry point of the app
            */

            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainFragContainer.id,HomeFragment())
                //addToBackStack(null)
                commit()
            }
        }

        //bottom navigation setup in the  main activity

        binding.mainBtmNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,HomeFragment())
                        //addToBackStack(null)
                        commit()

                    }
                    true
                }
                R.id.list_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,ListFragment())
                        //addToBackStack(null)
                        commit()

                    }
                    true
                }
                R.id.anime_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,AnimeDBFragment())
                        //addToBackStack(null)
                        commit()

                    }
                    true
                }
                R.id.read_frag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainFragContainer.id,ReadDBFragment())
                        //addToBackStack(null)
                        commit()

                    }
                    true
                }
                else -> false

            }
        }

    }
//on start of the function  wil load up data
    override fun onStart() {
        super.onStart()
    Log.d("TAG", "MainActivity :OnStart()")
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentUser=sharedPref.getString("currentUser","").toString()
        binding.currentuserimg.text=currentUser
        //fetchmangadata()



    }

    override fun onResume() {
        super.onResume()
        hidesystemUI()
        Log.d("TAG", "MainActivity :OnResume()")


    }

    private fun hidesystemUI() {
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    private fun fetchmangadata() {
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val db = Firebase.firestore
//        var MangaList:ArrayList<MangaData> = ArrayList()
        MangaList = ArrayList()

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

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "MainActivity :OnPause()")

    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "MainActivity :OnStop()")
    }
}