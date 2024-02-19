package com.nikhilsaps.anifynd.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nikhilsaps.anifynd.AddMangaActivity
import com.nikhilsaps.anifynd.MangaData
import com.nikhilsaps.anifynd.MangaPageActivity
import com.nikhilsaps.anifynd.MangaRecycDataset
import com.nikhilsaps.anifynd.OneTimeActivity
import com.nikhilsaps.anifynd.adapters.MangaRecycAdapter
import com.nikhilsaps.anifynd.databinding.FragmentReadDBBinding


class ReadDBFragment : Fragment() {


    private var _binding: FragmentReadDBBinding?=null
    private val PREFS_NAME = "MyPrefsFile"


    private val binding get() = _binding!!
    val db = Firebase.firestore




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "Read Fragment :OnCreate()")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReadDBBinding.inflate(inflater,container,false)

        Log.d("TAG", "Read Fragment :OnCreateView()")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TAG", "Read Fragment :OnViewCreated()")
        var mlist = context?.let { getMangaList(it) }
        var dataset :ArrayList<MangaRecycDataset> = ArrayList()


        if (mlist != null) {
            for ( data in mlist){
                dataset.add(MangaRecycDataset(data.name,data.imgsrc))
            }
        }
        val mangaAdapter= context?.let {
            MangaRecycAdapter(dataset, it){ position ->
                // Handle item click here
//                Toast.makeText(context, "Item clicked at position ${mlist?.get(position)}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context,MangaPageActivity::class.java).putExtra("pagedata",mlist?.get(position)?.name))
            }
        }
        val layoutManager = GridLayoutManager(context,3, RecyclerView.VERTICAL,false)
        binding.MangaRecycView.layoutManager = layoutManager
        binding.MangaRecycView.adapter=mangaAdapter

    }
    fun getMangaList(context: Context): ArrayList<MangaData> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("KEY_MANGA_LIST", null)
        val type = object : TypeToken<ArrayList<MangaData>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "Read Fragment :OnStart()")
        binding.flmangaAddBtn.setOnClickListener {

            val intent = Intent(context, AddMangaActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "Read Fragment :OnResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "Read Fragment :OnPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "Read Fragment :OnStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Nullify the binding to avoid memory leaks
        _binding = null
    }
}