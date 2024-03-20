package com.nikhilsaps.anifynd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.nikhilsaps.anifynd.adapters.AdapterReadViewRecycler
import com.nikhilsaps.anifynd.databinding.ActivityMainBinding
import com.nikhilsaps.anifynd.datamodels.ReadRecycDataModel
import com.nikhilsaps.anifynd.fragments.ReadViewFragment

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
    private val PREFS_NAME = "MyPrefsFile"
    private lateinit var MangaList:ArrayList<ReadRecycDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        fetchmangadata()

        binding.mainBtmNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.readableFrag ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.fragmentView.id,ReadViewFragment())
                        //addToBackStack(null)
                        commit()
                    }
                    true
                }

                else -> false

            }
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
                    var manga = ReadRecycDataModel(document.id,document.data["name"].toString(),document.data["nik_read"].toString(), document.data["assi_read"].toString(),document.data["desc"].toString())
                    MangaList.add(manga)
                    Log.d("TAG", "${document.id} => ${document.data}, ${MangaList[0]}")
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
}