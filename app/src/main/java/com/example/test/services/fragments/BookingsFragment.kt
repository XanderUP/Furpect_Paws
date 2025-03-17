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
        // ✅ Use `services_fragment.xml` instead of `fragment_bookings.xml`
        return inflater.inflate(R.layout.services_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Retrieve service details from arguments
        val serviceName = arguments?.getString("serviceName") ?: "Service Name"
        val serviceImageRes = arguments?.getInt("serviceImageRes") ?: R.drawable.ic_placeholder

        // ✅ Set "serviceTitle" in `services_fragment.xml`
        val serviceTitleTextView: TextView = view.findViewById(R.id.serviceTitle)
        serviceTitleTextView.text = serviceName

        // ✅ Handle back button
        val backButton = view.findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // ✅ Display the service image inside `serviceFragmentContainer` (optional)
        val serviceImageView = ImageView(requireContext()).apply {
            setImageResource(serviceImageRes)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        val container: ViewGroup = view.findViewById(R.id.serviceFragmentContainer)
        container.addView(serviceImageView)
    }
}
