package com.example.test.services.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.databinding.ServicesFragmentBinding
import com.example.test.services.ServicesAdapter
import com.example.test.services.ServicesItems

class GroomingServicesFragment : Fragment() {

    private var _binding: ServicesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var serviceAdapter: ServicesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ServicesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Set up RecyclerView properly
        serviceAdapter = ServicesAdapter(getServicesList())
        binding.recyclerViewServices.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewServices.adapter = serviceAdapter

        // ✅ Fix back button reference
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getServicesList(): List<ServicesItems> {
        return listOf(
            ServicesItems(
                R.drawable.grooming1,
                "Full Grooming Package",
                "A complete grooming service that includes a bath, haircut, nail trimming, and ear cleaning to keep your pet looking and feeling great."
            ),
            ServicesItems(
                R.drawable.grooming2,
                "Bath & Brush",
                "A thorough bath followed by brushing to remove loose fur and mats, leaving your pet clean and fresh."
            ),
            ServicesItems(
                R.drawable.grooming3,
                "Nail Trimming",
                "Professional nail clipping and filing to keep your pet's nails healthy and prevent overgrowth."
            ),
            ServicesItems(
                R.drawable.grooming4,
                "Ear Cleaning",
                "Cleaning and inspection of the pet's ears to prevent infections and maintain ear health."
            ),
            ServicesItems(
                R.drawable.grooming5,
                "Teeth Brushing",
                "Dental cleaning service to help maintain your pet's oral hygiene and freshen their breath."
            )
        )
    }
}