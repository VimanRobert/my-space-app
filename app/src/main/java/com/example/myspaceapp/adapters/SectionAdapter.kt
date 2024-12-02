package com.example.myspaceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myspaceapp.R
import com.example.myspaceapp.enums.Sections

class SectionAdapter : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {
    var sectionsList = Sections.entries
    var onClick: ((Sections) -> Unit)? = null

    inner class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val sectionItem: TextView = view.findViewById(R.id.section_name)
        private val cardViewSection: CardView = view.findViewById(R.id.card_view_section)

        fun bind(section: Sections) {
            sectionItem.text = section.title
            cardViewSection.setOnClickListener {
                onClick?.invoke(section)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.list_sections, parent, false)
        return SectionViewHolder(item)
    }

    override fun getItemCount(): Int {
        return sectionsList.size
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(sectionsList[position])
        holder.itemView.setOnClickListener {
            onClick?.invoke(sectionsList[position]) // Trigger the click listener with the section name
        }
    }
}