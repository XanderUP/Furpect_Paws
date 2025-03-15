package com.example.test.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ServicesItemBinding

class ServicesAdapter(private val serviceList: List<ServicesItems>) :
    RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ServicesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = serviceList[position]
        holder.bind(service)
    }

    override fun getItemCount(): Int = serviceList.size

    class ServiceViewHolder(private val binding: ServicesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(service: ServicesItems) {
            binding.serviceImage.setImageResource(service.imageRes)
            binding.serviceTitle.text = service.title
            binding.serviceDescription.text = service.description
        }
    }
}
