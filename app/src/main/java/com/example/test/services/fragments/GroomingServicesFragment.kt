package com.example.test.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.services.ServicesAdapter
import com.example.test.services.ServicesItems

class GroomingServicesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var servicesAdapter: ServicesAdapter
    private val groomingServicesList: MutableList<ServicesItems> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_grooming_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadGroomingServices()
    }

    private fun loadGroomingServices() {
        groomingServicesList.clear()

        // Add specialized services dynamically
        groomingServicesList.add(ServicesItems(R.drawable.grooming1, "Full Grooming Package",
            "A complete grooming service that includes a bath, haircut, nail trimming, and ear cleaning to keep your pet looking and feeling great."))
        groomingServicesList.add(ServicesItems(R.drawable.grooming2, "Bath & Brush",
            "A thorough bath followed by brushing to remove loose fur and mats, leaving your pet clean and fresh."))
        groomingServicesList.add(ServicesItems(R.drawable.grooming3, "Nail Trimming",
            "Professional nail clipping and filing to keep your pet's nails healthy and prevent overgrowth."))
        groomingServicesList.add(ServicesItems(R.drawable.grooming4, "Ear Cleaning",
            "Cleaning and inspection of the pet's ears to prevent infections and maintain ear health."))
        groomingServicesList.add(ServicesItems(R.drawable.grooming5, "Teeth Brushing",
            "Dental cleaning service to help maintain your pet's oral hygiene and freshen their breath."))
        servicesAdapter = ServicesAdapter(groomingServicesList) // ✅ Only pass the list
        recyclerView.adapter = servicesAdapter
    }
}