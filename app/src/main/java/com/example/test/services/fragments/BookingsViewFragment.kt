package com.example.test.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test.R

class BookingsViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bookings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Retrieve service details
        val serviceName = arguments?.getString("serviceName") ?: "Service Name"
        val serviceImageRes = arguments?.getInt("serviceImageRes") ?: R.drawable.ic_placeholder
        val serviceDesc = arguments?.getString("serviceDescription") ?: "No description available."

        // ✅ Find views inside `fragment_bookings.xml`
        val serviceTitle: TextView = view.findViewById(R.id.serviceName)
        val serviceImage: ImageView = view.findViewById(R.id.serviceImage)
        val serviceDescription: TextView = view.findViewById(R.id.serviceDescription)

        // ✅ Apply data to views
        serviceTitle.text = serviceName
        serviceImage.setImageResource(serviceImageRes)
        serviceDescription.text = serviceDesc
    }
}
