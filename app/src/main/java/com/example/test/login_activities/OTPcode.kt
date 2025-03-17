package com.example.test.login_activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class OTPcode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_otp_code)

        val confirmBtn = findViewById<Button>(R.id.ConfirmButton)
        val errorMessageTextView = findViewById<TextView>(R.id.otpError)

        val otpFields = listOf(
            findViewById<EditText>(R.id.otp_digit1),
            findViewById<EditText>(R.id.otp_digit2),
            findViewById<EditText>(R.id.otp_digit3),
            findViewById<EditText>(R.id.otp_digit4),
            findViewById<EditText>(R.id.otp_digit5),
            findViewById<EditText>(R.id.otp_digit6)
        )

        // Auto-switching OTP fields
        for (i in otpFields.indices) {
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 && i < otpFields.size - 1) {
                        otpFields[i + 1].requestFocus() // Move to the next field
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            // Handle backspace navigation
            otpFields[i].setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && i > 0) {
                    otpFields[i - 1].requestFocus() // Move to the previous field on backspace
                }
                false
            }
        }

        // Confirm OTP Function
        fun confirmOTP() {
            val otpCode = otpFields.joinToString("") { it.text.toString() }
            if (otpCode.length < 6) {
                errorMessageTextView.text = "Please enter a valid 6-digit OTP."
                errorMessageTextView.visibility = View.VISIBLE
            } else {
                errorMessageTextView.visibility = View.GONE
                val intent = Intent(this@OTPcode, Resetpass::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Confirm Button Click
        confirmBtn.setOnClickListener {
            confirmOTP()
        }
    }
}
