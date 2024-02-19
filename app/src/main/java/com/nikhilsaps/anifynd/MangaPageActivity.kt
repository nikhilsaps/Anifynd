package com.nikhilsaps.anifynd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.nikhilsaps.anifynd.databinding.ActivityAddMangaBinding
import com.nikhilsaps.anifynd.databinding.ActivityMangaPageBinding

class MangaPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMangaPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangaPageBinding.inflate(layoutInflater)
        val  view = binding.root
        setContentView(view)
        val MangaName:String = intent.getStringExtra("pageMangaName").toString()
        val McName:String = intent.getStringExtra("pageMcname").toString()
        val ImgeSrc:String = intent.getStringExtra("pageImgsrc").toString()
        val Assiread:String = intent.getStringExtra("pageAssiread").toString()
        val NikRead:String = intent.getStringExtra("pageNikread").toString()
        val Desc:String = intent.getStringExtra("pageDesc").toString()
        val docID:String = intent.getStringExtra("pageDocId").toString()

        val db = Firebase.firestore



//        binding.mangaPageText.text= ss
        Glide
            .with(this)
            .load(ImgeSrc)
            .placeholder(R.drawable.temp_drawable)
            .into(binding.mangaImage);
        binding.manganame.text= "Name : "+MangaName
        binding.MangaDesc.text=Desc+docID
        binding.mangaAssi.text="Assi. Read : "+Assiread
        binding.mangaNik.text="Nik. Read : "+NikRead
        binding.mangaMcName.text="MC Name : "+McName

        Log.d("TAG", "MangaPageActivity :OnCreate()")

        binding.editName.setOnClickListener {
         val city = hashMapOf(
            "name" to "Thousand year longing",

        )

        db.collection("MangaDB").document(docID)
            .set(city)
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
        }




    }

}