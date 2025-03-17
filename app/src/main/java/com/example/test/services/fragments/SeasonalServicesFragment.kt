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

class SeasonalServicesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var servicesAdapter: ServicesAdapter
    private val seasonalServicesList: MutableList<ServicesItems> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_seasonal_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadSeasonalServices()
    }

    private fun loadSeasonalServices() {
        seasonalServicesList.clear()

        // Add specialized services dynamically
        seasonalServicesList.add(ServicesItems(R.drawable.seasonal1, "Holiday Grooming Specials",
            "Special grooming services for holidays (e.g., Christmas, Halloween) to keep pets festive and well-groomed."))
        seasonalServicesList.add(ServicesItems(R.drawable.seasonal2, "Summer Grooming Specials",
            "Services focused on keeping pets cool and comfortable during the hot summer months."))
        seasonalServicesList.add(ServicesItems(R.drawable.seasonal3, "Winter Grooming Specials",
            "Services to protect pets from cold weather, including paw pad treatments and warm grooming."))
        // ✅ Pass click listener to adapter
        servicesAdapter = ServicesAdapter(seasonalServicesList) { service ->
            openBookingScreen(service)
        }
        recyclerView.adapter = servicesAdapter
    }

    private fun openBookingScreen(service: ServicesItems) {
        val bundle = Bundle().apply {
            putString("serviceName", service.title)
            putInt("serviceImageRes", service.imageRes)
        }

        val bookingFragment = BookingsFragment()
        bookingFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, bookingFragment)
            .addToBackStack(null)
            .commit()
    }
}