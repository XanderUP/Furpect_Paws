package com.example.test.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.services.HomeAdapter
import com.example.test.services.HomeItems

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private val servicesList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadServicesData()
    }

    private fun loadServicesData() {
        servicesList.clear()

        servicesList.add("GROOMING SERVICES")
        servicesList.add(HomeItems("Full Grooming", R.drawable.grooming1))
        servicesList.add(HomeItems("Bath & Brush", R.drawable.grooming2))

        servicesList.add("SPECIALIZED SERVICES")
        servicesList.add(HomeItems("Medicated Baths", R.drawable.special1))
        servicesList.add(HomeItems("Aromatherapy Baths", R.drawable.special2))

        servicesList.add("SEASONAL SERVICES")
        servicesList.add(HomeItems("Holiday Grooming Specials", R.drawable.seasonal1))
        servicesList.add(HomeItems("Summer Grooming Specials", R.drawable.seasonal2))

        servicesList.add("WELLNESS SERVICES")
        servicesList.add(HomeItems("Nutritional Consultation", R.drawable.wellness1))
        servicesList.add(HomeItems("Weight Management Programs", R.drawable.wellness2))

        servicesList.add("SENIOR SERVICES")
        servicesList.add(HomeItems("Extra-gentle Handling", R.drawable.senior1))
        servicesList.add(HomeItems("Joint-relief Hydrotherapy Bath", R.drawable.senior2))

        homeAdapter = HomeAdapter(
            requireContext(),
            servicesList,
            onViewAllClick = { sectionTitle ->
                val fragment = when (sectionTitle) {
                    "GROOMING SERVICES" -> GroomingServicesFragment()
                    "SPECIALIZED SERVICES" -> SpecializedServicesFragment()
                    "SEASONAL SERVICES" -> SeasonalServicesFragment()
                    "WELLNESS SERVICES" -> WellnessServicesFragment()
                    "SENIOR SERVICES" -> SeniorServicesFragment()
                    else -> GroomingServicesFragment() // Default fallback
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            onItemClick = { service ->
                // Handle item click if needed
            }
        )

        recyclerView.adapter = homeAdapter
    }
}