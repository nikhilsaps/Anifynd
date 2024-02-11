package com.nikhilsaps.anifynd.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikhilsaps.anifynd.R

class MangaRecycAdapter(
    private val dataset: Array<String>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MangaRecycAdapter.MangaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manga_card, parent, false)
        return MangaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(dataset[position])
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    inner class MangaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.manga_card_text)

        fun bind(data: String) {
            textView.text = data
        }
    }
}