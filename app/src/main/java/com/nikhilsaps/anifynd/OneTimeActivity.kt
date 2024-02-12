package com.nikhilsaps.anifynd

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nikhilsaps.anifynd.databinding.ActivityOneTimeBinding

class OneTimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOneTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneTimeBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        binding.userAssi.setOnClickListener{
            Toast.makeText(this,"the User is set to assi",Toast.LENGTH_SHORT).show()

            editor.putString("currentUser", "Assi")
            editor.apply()
            startActivity(Intent(this , MainActivity::class.java))
            finish()

        }
        binding.userNikhil.setOnClickListener{
            Toast.makeText(this,"the User is set to nikhil",Toast.LENGTH_SHORT).show()
            editor.putString("currentUser", "Nikhil")
            editor.apply()
            startActivity(Intent(this , MainActivity::class.java))
            finish()

        }
    }
}