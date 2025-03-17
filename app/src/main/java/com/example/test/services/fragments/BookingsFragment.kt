package com.example.test.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test.R

class BookingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ✅ Use `services_fragment.xml` as the base layout
        return inflater.inflate(R.layout.services_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Handle back button click
        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // ✅ Set service title to "My Bookings"
        val serviceTitle: TextView = view.findViewById(R.id.serviceTitle)
        serviceTitle.text = "My Bookings"

        // ✅ Retrieve service details from arguments
        val serviceName = arguments?.getString("serviceName") ?: "Service Name"
        val serviceImageRes = arguments?.getInt("serviceImageRes") ?: R.drawable.ic_placeholder
        val serviceDesc = arguments?.getString("serviceDescription") ?: "No description available."

        // ✅ Load `fragment_bookings.xml` inside `serviceFragmentContainer`
        val bookingsViewFragment = BookingsViewFragment().apply {
            arguments = Bundle().apply {
                putString("serviceName", serviceName)
                putInt("serviceImageRes", serviceImageRes)
                putString("serviceDescription", serviceDesc)
            }
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.serviceFragmentContainer, bookingsViewFragment)
            .commit()
    }
}
