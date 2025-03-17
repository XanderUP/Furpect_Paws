package com.example.test.services

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.services.fragments.MyBookingsFragment
import com.example.test.services.fragments.HomeFragment
import com.example.test.services.fragments.NotificationsFragment
import com.example.test.services.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainHome : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        bottomNavigationView = findViewById(R.id.bottomNav)

        setupBottomNavigation()

        // ✅ Load HomeFragment by default
        replaceFragment(HomeFragment())
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment()) // ✅ Shows Search Bar & RecyclerView
                    true
                }
                R.id.nav_bookings -> {
                    replaceFragment(MyBookingsFragment()) // ❌ Hides Search Bar & RecyclerView
                    true
                }
                R.id.nav_notification -> {
                    replaceFragment(NotificationsFragment()) // ❌ Hides Search Bar & RecyclerView
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment()) // ❌ Hides Search Bar & RecyclerView
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
