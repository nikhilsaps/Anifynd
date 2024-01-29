package com.nikhilsaps.anifynd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikhilsaps.anifynd.databinding.ActivityMainBinding
import com.nikhilsaps.anifynd.fragments.AnimeDBFragment
import com.nikhilsaps.anifynd.fragments.HomeFragment
import com.nikhilsaps.anifynd.fragments.ListFragment
import com.nikhilsaps.anifynd.fragments.ReadDBFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //run this once  to  make  the home fragment appear  on launch
        supportFragmentManager.beginTransaction().apply {
            replace(binding.mainFragContainer.id,HomeFragment())
            addToBackStack(null)
            commit()
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
        binding.temptext.text= "ni mai ni rok sakta  tujhe "
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