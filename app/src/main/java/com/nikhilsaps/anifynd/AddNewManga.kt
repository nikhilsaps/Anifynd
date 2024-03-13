package com.nikhilsaps.anifynd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.nikhilsaps.anifynd.databinding.ActivityAddNewMangaBinding

class AddNewManga : AppCompatActivity() {
    private lateinit var binding:ActivityAddNewMangaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNewMangaBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        binding.submitNewManga.setOnClickListener {
            var  newMangaName=binding.newmangaName.text
            var newMangaDesc=binding.newmangaDesc.text
            var newMangaAssiread =binding.newmangaAssiread.text
            var newMangaNikread =binding.newmangaNikread.text


            addDataInDB(newMangaAssiread.toString(),newMangaDesc.toString(),newMangaName.toString(),newMangaNikread.toString())

        }

    }

    fun addDataInDB(assi_read:String,desc:String,name:String,nik_read:String){
        val user = hashMapOf(
            "assi_read" to assi_read,
            "desc" to desc,
            "name" to name,
            "nik_read" to nik_read
        )
        val db = Firebase.firestore
        db.collection("MangaDB")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                finish()
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }
}