package com.example.test.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.services.fragments.GroomingServicesFragment
import com.example.test.services.fragments.SeasonalServicesFragment
import com.example.test.services.fragments.SeniorServicesFragment
import com.example.test.services.fragments.SpecializedServicesFragment
import com.example.test.services.fragments.WellnessServicesFragment

class ServicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.services_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceCategory = arguments?.getString("serviceCategory") ?: "Services"
        val titleTextView = view.findViewById<TextView>(R.id.serviceTitle)
        titleTextView.text = serviceCategory // Dynamically update the title
        val backButton = view.findViewById<ImageView>(R.id.backButton) // ✅ Fix back button
        backButton.setOnClickListener {
            requireActivity().onBackPressed() // ✅ This will go back to HomeFragment
        }

        // Load the correct category fragment inside `serviceFragmentContainer`
        val categoryFragment: Fragment = when (serviceCategory) {
            "GROOMING SERVICES" -> GroomingServicesFragment()
            "SPECIALIZED SERVICES" -> SpecializedServicesFragment()
            "SEASONAL SERVICES" -> SeasonalServicesFragment()
            "WELLNESS SERVICES" -> WellnessServicesFragment()
            "SENIOR SERVICES" -> SeniorServicesFragment()
            else -> GroomingServicesFragment()
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.serviceFragmentContainer, categoryFragment)
            .commit()
    }
}
