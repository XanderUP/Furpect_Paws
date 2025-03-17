package com.example.test.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class ServicesAdapter(
    private val serviceList: List<ServicesItems>,
    private val onItemClick: (ServicesItems) -> Unit  // ✅ Callback for item clicks
) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.services_item, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = serviceList[position]
        holder.bind(service)

        // ✅ Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(service)  // ✅ Send clicked item data
        }
    }

    override fun getItemCount(): Int = serviceList.size

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val serviceImage: ImageView = itemView.findViewById(R.id.serviceImage)
        private val serviceTitle: TextView = itemView.findViewById(R.id.serviceTitle)
        private val serviceDescription: TextView = itemView.findViewById(R.id.serviceDescription)

        fun bind(service: ServicesItems) {
            serviceImage.setImageResource(service.imageRes)
            serviceTitle.text = service.title
            serviceDescription.text = service.description
        }
    }
}
