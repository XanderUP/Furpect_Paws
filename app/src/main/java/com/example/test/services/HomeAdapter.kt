package com.example.test.services

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class HomeAdapter(
    private val context: Context,
    private val items: List<Any>,
    private val onViewAllClick: (String) -> Unit, // Callback for "View All" button
    private val onItemClick: (HomeItems) -> Unit // Callback for clicking service items
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_SECTION = 0
    private val VIEW_TYPE_ITEM = 1

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is String) VIEW_TYPE_SECTION else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SECTION) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.home_sections, parent, false)
            SectionViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.home_service, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is SectionViewHolder && item is String) {
            holder.sectionTitle.text = item
            holder.viewAll.setOnClickListener {
                onViewAllClick(item)
            }
        } else if (holder is ItemViewHolder && item is HomeItems) {
            holder.bind(item, onItemClick)
        }
    }

    override fun getItemCount(): Int = items.size

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sectionTitle: TextView = view.findViewById(R.id.sectionTitle)
        val viewAll: TextView = view.findViewById(R.id.viewAll)

        fun bind(sectionName: String, onViewAllClick: (String) -> Unit) {
            sectionTitle.text = sectionName
            viewAll.setOnClickListener {
                onViewAllClick(sectionName) // Pass the section name to handle click
            }
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val serviceImage: ImageView = itemView.findViewById(R.id.serviceImage)
        private val serviceName: TextView = itemView.findViewById(R.id.serviceName)

        fun bind(service: HomeItems, onItemClick: (HomeItems) -> Unit) {
            serviceName.text = service.title
            serviceImage.setImageResource(service.imageRes) // Load local drawable

            itemView.setOnClickListener {
                onItemClick(service)
            }
        }
    }
}
