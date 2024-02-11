package com.nikhilsaps.anifynd.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.nikhilsaps.anifynd.MangaData
import com.nikhilsaps.anifynd.adapters.MangaRecycAdapter
import com.nikhilsaps.anifynd.databinding.FragmentReadDBBinding


class ReadDBFragment : Fragment() {


    private var _binding: FragmentReadDBBinding?=null


    private val binding get() = _binding!!
    val db = Firebase.firestore




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReadDBBinding.inflate(inflater,container,false)

        Toast.makeText(context,"hello to Read creating ", Toast.LENGTH_SHORT).show()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,"hello to read created  ", Toast.LENGTH_SHORT).show()
        var dataset= arrayOf("nikhil")

       // var MangaData:MutableList<MangaData>

        db.collection("MangaDB")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    Log.d("TAG", "${document.id} => ${document.data["name"]}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }



        //val dataset = arrayOf("January", "February", "March","April","May","June","July","August","September","October","Novenber","December",  "January", "February", "March","April","May","June","July","August","September","October","Novenber","December")

        val mangaAdapter=MangaRecycAdapter(dataset){ position ->
            // Handle item click here
            Toast.makeText(context, "Item clicked at position ${dataset[position]}", Toast.LENGTH_SHORT).show()
        }
        val layoutManager = GridLayoutManager(context,3, RecyclerView.VERTICAL,false)
        binding.MangaRecycView.layoutManager = layoutManager
        binding.MangaRecycView.adapter=mangaAdapter



    }

    override fun onStart() {
        super.onStart()
        Log.d("READ", "onRestart: Activity start")

    }

    override fun onResume() {
        super.onResume()
        Log.d("READ", "onRestart: Activity resumed")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Nullify the binding to avoid memory leaks
        _binding = null
    }
}