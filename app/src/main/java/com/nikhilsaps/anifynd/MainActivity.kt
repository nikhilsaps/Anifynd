package com.nikhilsaps.anifynd

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ktx.Firebase
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