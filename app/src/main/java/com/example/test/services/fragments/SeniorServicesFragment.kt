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

class SeniorServicesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var servicesAdapter: ServicesAdapter
    private val seniorServicesList: MutableList<ServicesItems> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_senior_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadSeniorServices()
    }

    private fun loadSeniorServices() {
        seniorServicesList.clear()

        // Add specialized services dynamically
        seniorServicesList.add(ServicesItems(R.drawable.senior1, "Extra-Gentle Handling",
            "For elderly or nervous dogs."))
        seniorServicesList.add(ServicesItems(R.drawable.senior2, "Joint-Relief Hydrotherapy Bath",
            "Helps relieve arthritis pain."))
        seniorServicesList.add(ServicesItems(R.drawable.senior3, "Disability-Friendly Grooming",
            "Customized care for special-needs dogs."))
        servicesAdapter = ServicesAdapter(seniorServicesList) // ✅ Only pass the list
        recyclerView.adapter = servicesAdapter
    }
}