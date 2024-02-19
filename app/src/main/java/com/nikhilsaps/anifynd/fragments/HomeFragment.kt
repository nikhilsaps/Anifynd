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
        Log.d("TAG", "Home Fragment :OnCreate()")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        Log.d("TAG", "Home Fragment :OnCreateView()")


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "Home Fragment :OnViewCreated()")


    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "Home Fragment :OnStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "Home Fragment :OnResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "Home Fragment :OnPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "Home Fragment :OnStop()")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Nullify the binding to avoid memory leaks
        _binding = null
    }
}