package com.nikhilsaps.anifynd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikhilsaps.anifynd.databinding.ActivityAddMangaBinding
import com.nikhilsaps.anifynd.databinding.ActivityMangaPageBinding

class MangaPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMangaPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangaPageBinding.inflate(layoutInflater)
        val  view = binding.root
        setContentView(view)
        val ss:String = intent.getStringExtra("pagedata").toString()
        binding.mangaPageText.text= ss


    }

}