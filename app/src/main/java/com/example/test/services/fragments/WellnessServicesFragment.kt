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

class WellnessServicesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var servicesAdapter: ServicesAdapter
    private val wellnessServicesList: MutableList<ServicesItems> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_wellness_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadWellnessServices()
    }

    private fun loadWellnessServices() {
        wellnessServicesList.clear()

        // Add specialized services dynamically
        wellnessServicesList.add(ServicesItems(R.drawable.wellness1, "Nutritional Consultation",
            "Guidance on pet nutrition and diet to ensure pets are healthy and well-fed."))
        wellnessServicesList.add(ServicesItems(R.drawable.wellness2, "Weight Management Programs",
            "Programs designed to help pets achieve and maintain a healthy weight through diet and exercise."))
        servicesAdapter = ServicesAdapter(wellnessServicesList) // ✅ Only pass the list
        recyclerView.adapter = servicesAdapter
    }
}