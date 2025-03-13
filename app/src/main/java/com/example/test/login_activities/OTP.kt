package com.example.test.login_activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class OTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_otp)

        //sendBtn is essentially a next-screen button until backend is implemented
        val sendBtn = findViewById<Button>(R.id.otpButton)
        sendBtn.setOnClickListener{
            val intent = Intent(this@OTP, Resetpass::class.java)
            startActivity(intent)
        }
    }
}