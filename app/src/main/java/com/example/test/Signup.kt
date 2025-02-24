package com.example.test

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.test.models.ResponseModel
import com.example.test.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var signupButton: Button
    private lateinit var errorMessageTextView: TextView
    private lateinit var backToLoginTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        // Initialize UI elements
        nameEditText = findViewById(R.id.SignupName)
        emailEditText = findViewById(R.id.SignupEmail)
        passwordEditText = findViewById(R.id.SignupPassword)
        confirmPasswordEditText = findViewById(R.id.SignupConfirm)
        phoneEditText = findViewById(R.id.SignupContact)
        signupButton = findViewById(R.id.SignupButton)
        errorMessageTextView = findViewById(R.id.SignupError)
        backToLoginTextView = findViewById(R.id.backToLogin)

        // Set clickable "Log In" text
        setupLoginRedirect()

        // Set click listener for signup
        signupButton.setOnClickListener {
            registerUser()
        }
    }

    private fun setupLoginRedirect() {
        val text = "Already have an Account? Log In"
        val spannableString = SpannableString(text)

        val start = text.indexOf("Log In")
        val end = start + "Log In".length

        spannableString.setSpan(UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@SignupActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        backToLoginTextView.text = spannableString
        backToLoginTextView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun registerUser() {
        val fullName = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()
        val phone = phoneEditText.text.toString().trim()

        // Validation checks
        when {
            fullName.isEmpty() -> showError("Full Name is required")
            email.isEmpty() -> showError("Email is required")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> showError("Invalid email format")
            password.length < 4 -> showError("Password must contain at least 4 characters")
            password != confirmPassword -> showError("Passwords do not match")
            phone.isEmpty() -> showError("Phone number is required")
            phone.length != 11 -> showError("Phone number must be 11 digits")
            else -> {
                errorMessageTextView.visibility = View.GONE // Hide error message
                sendSignupRequest(fullName, email, password, phone)
            }
        }
    }

    private fun showError(message: String) {
        errorMessageTextView.text = message
        errorMessageTextView.visibility = View.VISIBLE
    }

    private fun sendSignupRequest(fullName: String, email: String, password: String, phone: String) {
        RetrofitClient.instance.registerUser(fullName, email, password, phone)
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@SignupActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignupActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        showError("Signup failed. Please try again.")
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    showError("Error: ${t.message}")
                }
            })
    }
}
