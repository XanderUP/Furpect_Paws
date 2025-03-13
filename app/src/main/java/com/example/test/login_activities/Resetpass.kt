package com.example.test.login_activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class Resetpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_reset_password)

        // Initialize UI elements
        val passEditText = findViewById<EditText>(R.id.ResetPassword)
        val confirmEditText = findViewById<EditText>(R.id.ResetConfirm)
        val resetButton = findViewById<Button>(R.id.ResetButton)
        val errorMessageTextView = findViewById<TextView>(R.id.ResetError)

        //error message
        fun showError(message: String) {
            errorMessageTextView.text = message
            errorMessageTextView.visibility = View.VISIBLE
        }

        //check if field is empty and display error
        fun forgotPassword() {
            val pass = passEditText.text.toString().trim()
            val confirmPassword = confirmEditText.text.toString().trim()
            when {
                pass.isEmpty() -> showError("Phone number is required")
                pass.length < 4 -> showError("Password must contain at least 4 characters")
                pass != confirmPassword -> showError("Passwords do not match")
                else -> {
                    errorMessageTextView.visibility = View.GONE // Hide error message
                    val intent = Intent(this@Resetpass, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        //button for the functions above
        resetButton.setOnClickListener {
            forgotPassword()
        }
    }
}