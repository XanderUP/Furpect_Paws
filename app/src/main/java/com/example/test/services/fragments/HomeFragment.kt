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
import com.example.test.services.ServicesFragment

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
        servicesList.add(HomeItems("Full Grooming", R.drawable.grooming1, "A complete grooming service that includes a bath, haircut, nail trimming, and ear cleaning to keep your pet looking and feeling great."))
        servicesList.add(HomeItems("Bath & Brush", R.drawable.grooming2, "A thorough bath followed by brushing to remove loose fur and mats, leaving your pet clean and fresh."))

        servicesList.add("SPECIALIZED SERVICES")
        servicesList.add(HomeItems("Medicated Baths", R.drawable.special1, "For pets with skin conditions."))
        servicesList.add(HomeItems("Aromatherapy Baths", R.drawable.special2, "Calming essential oils for relaxation."))

        servicesList.add("SEASONAL SERVICES")
        servicesList.add(HomeItems("Holiday Grooming Specials", R.drawable.seasonal1, "Special grooming services for holidays (e.g., Christmas, Halloween) to keep pets festive and well-groomed."))
        servicesList.add(HomeItems("Summer Grooming Specials", R.drawable.seasonal2, "Services focused on keeping pets cool and comfortable during the hot summer months."))

        servicesList.add("WELLNESS SERVICES")
        servicesList.add(HomeItems("Nutritional Consultation", R.drawable.wellness1, "Guidance on pet nutrition and diet to ensure pets are healthy and well-fed."))
        servicesList.add(HomeItems("Weight Management Programs", R.drawable.wellness2, "Programs designed to help pets achieve and maintain a healthy weight through diet and exercise."))

        servicesList.add("SENIOR SERVICES")
        servicesList.add(HomeItems("Extra-gentle Handling", R.drawable.senior1, "For elderly or nervous dogs."))
        servicesList.add(HomeItems("Joint-relief Hydrotherapy Bath", R.drawable.senior2, "Helps relieve arthritis pain."))

        homeAdapter = HomeAdapter(
            requireContext(),
            servicesList,
            onViewAllClick = { sectionTitle ->
                val fragment = ServicesFragment().apply {
                    arguments = Bundle().apply {
                        putString("serviceCategory", sectionTitle)
                    }
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            onItemClick = { service ->
                openBookingScreen(service) // ✅ Now using the extracted function
            }
        )

        recyclerView.adapter = homeAdapter
    }

    // ✅ Extracted function to open `BookingsFragment`
    private fun openBookingScreen(service: HomeItems) {
        val bundle = Bundle().apply {
            putString("serviceName", service.title) // ✅ Corrected
            putInt("serviceImageRes", service.imageRes)
            putString("serviceDescription", service.description) // ✅ Add this to pass the description
        }

        val bookingFragment = BookingsFragment()
        bookingFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, bookingFragment)
            .addToBackStack(null)
            .commit()
    }
}
