package com.example.test.login_activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class Forgot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_forgot)

        // Initialize UI elements
        val emailEditText = findViewById<EditText>(R.id.ForgotEmail)
        val forgotButton = findViewById<Button>(R.id.ForgotButton)
        val errorMessageTextView = findViewById<TextView>(R.id.ForgotError)

        //error message
        fun showError(message: String) {
            errorMessageTextView.text = message
            errorMessageTextView.visibility = View.VISIBLE
        }

        //check if field is empty and display error
        fun forgotPassword() {
            val email = emailEditText.text.toString().trim()
            when {
                email.isEmpty() -> showError("Email is required")
                !Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() -> showError("Invalid email format")
                else -> {
                    errorMessageTextView.visibility = View.GONE // Hide error message
                    val intent = Intent(this@Forgot, OTPcode::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        //button for the functions above
        forgotButton.setOnClickListener {
            forgotPassword()
        }
    }
}