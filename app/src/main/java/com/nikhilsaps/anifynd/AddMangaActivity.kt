package com.nikhilsaps.anifynd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.nikhilsaps.anifynd.databinding.ActivityAddMangaBinding

class AddMangaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMangaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddMangaBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        Log.d("TAG", "AddMangaActivity :OnCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "AddMangaActivity :OnStart()")
        binding.submitNewManga.setOnClickListener {
            var  newMangaName=binding.newmangaName.text
            var newMangaMcname=binding.newmangaMcname.text
            var newMangaDesc=binding.newmangaDesc.text
            var newMangaAssiread =binding.newmangaAssiread.text
            var newMangaNikread =binding.newmangaNikread.text


            addDataInDB(newMangaAssiread.toString(),newMangaDesc.toString(),newMangaMcname.toString(),newMangaName.toString(),newMangaNikread.toString())

        }

    }
    fun addDataInDB(assi_read:String,desc:String,mcname:String,name:String,nik_read:String){
        val user = hashMapOf(
            "assi_read" to assi_read,
            "desc" to desc,
            "imgsrc" to "imgsrc",
            "mcname" to mcname,
            "name" to name,
            "nik_read" to nik_read
        )
        val db = Firebase.firestore
        db.collection("MangaDB")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }
}