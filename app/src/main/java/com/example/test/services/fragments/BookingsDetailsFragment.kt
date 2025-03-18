package com.example.test.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test.R

class BookingsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bookings_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Get additional details if needed
        val duration = arguments?.getString("duration") ?: "1 hour"
        val priceRange = arguments?.getString("priceRange") ?: "₱2,500 - ₱3,500"

        // ✅ Set details dynamically
        val durationValue = view.findViewById<TextView>(R.id.durationValue)
        val priceRangeValue = view.findViewById<TextView>(R.id.priceRangeValue)

        durationValue.text = duration
        priceRangeValue.text = priceRange
    }
}
