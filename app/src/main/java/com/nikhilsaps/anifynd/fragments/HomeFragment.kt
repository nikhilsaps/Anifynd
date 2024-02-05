package com.nikhilsaps.anifynd.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nikhilsaps.anifynd.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null

    private val binding get() = _binding!!
//    val db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        Toast.makeText(context,"hello to home creating ", Toast.LENGTH_SHORT).show()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,"hello to home created  ", Toast.LENGTH_SHORT).show()

        // Now you can access views using binding
        // Write a message to the database

//        db.collection("AnimeDB")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    binding.textfirestore.text="${document.id} => ${document.data}"
//                    Log.d("TAG", "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("TAG", "Error getting documents.", exception)
//            }


    }

    override fun onStart() {
        super.onStart()
        Log.d("Home", "onRestart: Activity start")

    }

    override fun onResume() {
        super.onResume()
        Log.d("Home", "onRestart: Activity resumed")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Nullify the binding to avoid memory leaks
        _binding = null
    }
}