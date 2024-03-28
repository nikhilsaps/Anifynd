package com.nikhilsaps.anifynd.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nikhilsaps.anifynd.AddNewManga
import com.nikhilsaps.anifynd.adapters.AdapterReadViewRecycler
import com.nikhilsaps.anifynd.databinding.FragmentReadViewBinding
import com.nikhilsaps.anifynd.datamodels.ReadRecycDataModel


class ReadViewFragment : Fragment() {
    private var _binding: FragmentReadViewBinding?=null
    private val binding get() = _binding!!
    private val PREFS_NAME = "MyPrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding =FragmentReadViewBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dataset: ArrayList<ReadRecycDataModel> = ArrayList()
        //dataset.add(ReadRecycDataModel("21","nikk","sad","das","dasdasd"))
        var mlist = context?.let { getMangaList(it) }
        if (mlist != null) {
            for ( data in mlist){
                dataset.add(ReadRecycDataModel(data.DocID,data.name,data.nikreadcount,data.assireadcount,data.descofmanga))
            }
        }

        var mangaAdapter: AdapterReadViewRecycler? = null
        context?.let { ctx ->
            mangaAdapter = AdapterReadViewRecycler(dataset, ctx) { position ->
                // Handle item click here
                val selectedItem = mangaAdapter?.filteredList?.getOrNull(position)
                selectedItem?.let { item ->
                    Log.d("TAG", "ooouch how dare ${item.name}")
                }
            }
        }

        val layoutManager = LinearLayoutManager(context)
        binding.readfragmentrecyclerview.layoutManager = layoutManager
        binding.readfragmentrecyclerview.adapter = mangaAdapter

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                mangaAdapter?.filter(s.toString())
            }
        })
        binding.addNewMangafloatingbtn.setOnClickListener {
            startActivity(Intent(context, AddNewManga::class.java))
        }
    }

    fun getMangaList(context: Context): ArrayList<ReadRecycDataModel> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("KEY_MANGA_LIST", null)
        val type = object : TypeToken<ArrayList<ReadRecycDataModel>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }
}