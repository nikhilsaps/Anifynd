package com.nikhilsaps.anifynd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikhilsaps.anifynd.R
import com.nikhilsaps.anifynd.datamodels.ReadRecycDataModel

class AdapterReadViewRecycler(
    private var dataset: ArrayList<ReadRecycDataModel>,
    private val context: Context,
    private val onItemClick: (position: Int) -> Unit

) : RecyclerView.Adapter<AdapterReadViewRecycler.MangaViewHolder>() {
     var filteredList: ArrayList<ReadRecycDataModel> = ArrayList()

    init {
        filteredList.addAll(dataset) // Initialize filteredList with dataset
    }

    fun filter(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(dataset)
        } else {
            val lowerCaseQuery = query.toLowerCase()
            for (item in dataset) {
                if (item.name.toLowerCase().contains(lowerCaseQuery)) {
                    filteredList.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.readableitem, parent, false)
        return MangaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(filteredList[position])
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    inner class MangaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameofmanga: TextView = view.findViewById(R.id.nameofmanga)
        private val nikreadcount: TextView = view.findViewById(R.id.nikreadcount)
        private val assireadcount: TextView = view.findViewById(R.id.assireadcount)
        private val descofmanga: TextView = view.findViewById(R.id.descofmanga)

        fun bind(data: ReadRecycDataModel) {
            nameofmanga.text = data.name
            nikreadcount.text = data.nikreadcount
            assireadcount.text = data.assireadcount
            descofmanga.text = data.descofmanga
        }
    }
}