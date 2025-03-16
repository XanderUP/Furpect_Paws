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

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadSpecializedServices()
    }

    private fun loadSpecializedServices() {
        specializedServicesList.clear()

        // Add specialized services dynamically
        specializedServicesList.add(ServicesItems(R.drawable.special1, "Medicated Baths",
            "For pets with skin conditions."))
        specializedServicesList.add(ServicesItems(R.drawable.special2, "Aromatherapy Baths",
            "Calming essential oils for relaxation."))
        specializedServicesList.add(ServicesItems(R.drawable.special3, "Anal Gland Expression",
            "Prevents discomfort and infections."))
        specializedServicesList.add(ServicesItems(R.drawable.special4, "Hand Stripping",
            "For wire-haired breeds to maintain coat texture."))
        specializedServicesList.add(ServicesItems(R.drawable.special5, "Creative Grooming",
            "Dyeing, nail polish, or unique styling."))
        servicesAdapter = ServicesAdapter(specializedServicesList) // ✅ Only pass the list
        recyclerView.adapter = servicesAdapter
    }
}