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

class SpecializedServicesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var servicesAdapter: ServicesAdapter
    private val specializedServicesList: MutableList<ServicesItems> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_specialized_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewSpecializedServices)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadSpecializedServices()
    }

    private fun loadSpecializedServices() {
        specializedServicesList.clear()

        // Add specialized services dynamically
        specializedServicesList.add(ServicesItems(R.drawable.special1, "Medicated Baths", "u i i a u"))
        specializedServicesList.add(ServicesItems(R.drawable.special2, "Aromatherapy Baths", "a i a u"))

        servicesAdapter = ServicesAdapter(specializedServicesList) // ✅ Only pass the list
        recyclerView.adapter = servicesAdapter
    }
}