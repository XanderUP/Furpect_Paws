package com.example.test.login_activities

import com.example.test.services.MainHome
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class Welcome : AppCompatActivity() {
    private fun navigateToHome() {
        startActivity(Intent(this@Welcome, MainHome::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_welcome)

        val sendBtn = findViewById<Button>(R.id.WelcomeButton)
        sendBtn.setOnClickListener {
            navigateToHome()
        }
    }
}
