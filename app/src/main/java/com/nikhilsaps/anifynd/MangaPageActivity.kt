package com.nikhilsaps.anifynd

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
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
        val view = binding.root
        setContentView(view)
        val MangaName: String = intent.getStringExtra("pageMangaName").toString()
        val McName: String = intent.getStringExtra("pageMcname").toString()
        val ImgeSrc: String = intent.getStringExtra("pageImgsrc").toString()
        val Assiread: String = intent.getStringExtra("pageAssiread").toString()
        val NikRead: String = intent.getStringExtra("pageNikread").toString()
        val Desc: String = intent.getStringExtra("pageDesc").toString()
        val docID: String = intent.getStringExtra("pageDocId").toString()

        val db = Firebase.firestore


//        binding.mangaPageText.text= ss
        Glide
            .with(this)
            .load(ImgeSrc)
            .placeholder(R.drawable.temp_drawable)
            .into(binding.mangaImage);
        binding.manganame.text = "Name : " + MangaName
        binding.MangaDesc.text = Desc + docID
        binding.mangaAssi.text = "Assi. Read : " + Assiread
        binding.mangaNik.text = "Nik. Read : " + NikRead
        binding.mangaMcName.text = "MC Name : " + McName

        Log.d("TAG", "MangaPageActivity :OnCreate()")

        binding.editName.setOnClickListener {
           showDialog("Name",MangaName,McName,ImgeSrc,Assiread,NikRead,Desc,docID)
        }
        binding.editDesc.setOnClickListener {
           showDialog("Description",MangaName,McName,ImgeSrc,Assiread,NikRead,Desc,docID)

        }
        binding.editMcname.setOnClickListener {
          showDialog("Edit MC name",MangaName,McName,ImgeSrc,Assiread,NikRead,Desc,docID)

        }


        binding.editNread.setOnClickListener {
            showDialog("Nikhil Read",MangaName,McName,ImgeSrc,Assiread,NikRead,Desc,docID)

        }

        binding.editAread.setOnClickListener {
            showDialog("Assi Read",MangaName,McName,ImgeSrc,Assiread,NikRead,Desc,docID)

        }





    }
    fun showDialog(initialText: String,name:String,mcname: String,imgsrc: String,assi_read: String,nik_read: String,desc: String,docID: String){
        // Create dialog instance
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.editboxforpage)

        // Initialize views
        val editText = dialog.findViewById<EditText>(R.id.edit_data)
        val title = dialog.findViewById<TextView>(R.id.editbox_title)
        val save_btn = dialog.findViewById<Button>(R.id.save_btn)
        val cncl_btn = dialog.findViewById<Button>(R.id.cancel_btn)
        editText.hint=initialText
        title.text = "Enter the "+initialText
        // Set initial text
        save_btn.setOnClickListener {
           var data=  editText.text.toString()
            when (initialText) {
                "Name" -> {
                    // Do something for "name"
                    senddata(data,mcname,imgsrc,assi_read,nik_read,desc,docID)
                }
                "Edit MC name" -> {
                    senddata(name,data,imgsrc,assi_read,nik_read,desc,docID)
                    // Do something for "mcname"
                }
                "Assi Read" -> {
                    senddata(name,mcname,imgsrc,data,nik_read,desc,docID)
                    // Do something for "assi_read"
                }
                "Nikhil Read" -> {
                    senddata(name,mcname,imgsrc,assi_read,data,desc,docID)
                    // Do something for "nik_read"
                }
                "Description" -> {
                    senddata(name,mcname,imgsrc,assi_read,nik_read,data,docID)
                    // Do something for "desc"
                }
                else -> {

                }
            }
            dialog.dismiss()
        }
        cncl_btn.setOnClickListener {
          dialog.dismiss()
        }
        // Show the dialog
        dialog.show()
    }
    fun senddata(name:String,mcname: String,imgsrc: String,assi_read: String,nik_read: String,desc: String,docID: String){
        val db = Firebase.firestore
        val data = hashMapOf(
            "assi_read" to assi_read,
            "desc" to desc,
            "imgsrc" to imgsrc,
            "mcname" to mcname,
            "name" to name,
            "nik_read" to nik_read
        )

        db.collection("MangaDB").document(docID)
            .set(data)
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
    }





}
