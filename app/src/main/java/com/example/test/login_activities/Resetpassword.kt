package com.example.test.login_activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R
import com.example.test.network.ApiService
import com.example.test.network.RetrofitClient
import com.example.test.models.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Resetpassword : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_reset_password)

        // FIX: Use RetrofitClient.instance instead of getClient()
        apiService = RetrofitClient.instance

        email = intent.getStringExtra("email") ?: ""

        val passEditText = findViewById<EditText>(R.id.ResetPassword)
        val confirmEditText = findViewById<EditText>(R.id.ResetConfirm)
        val resetButton = findViewById<Button>(R.id.ResetButton)
        val errorMessageTextView = findViewById<TextView>(R.id.ResetError)

        fun showError(message: String) {
            errorMessageTextView.text = message
            errorMessageTextView.visibility = View.VISIBLE
        }

        fun resetPassword() {
            val pass = passEditText.text.toString().trim()
            val confirmPassword = confirmEditText.text.toString().trim()

            when {
                pass.isEmpty() -> showError("This field is required")
                pass.length < 4 -> showError("Password must contain at least 4 characters")
                pass != confirmPassword -> showError("Passwords do not match")
                else -> {
                    errorMessageTextView.visibility = View.GONE
                    callResetPasswordApi(email, pass)
                }
            }
        }

        resetButton.setOnClickListener {
            resetPassword()
        }
    }

    private fun callResetPasswordApi(email: String, newPassword: String) {
        apiService.resetPassword(email, newPassword).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful && response.body()?.error == false) {
                    Toast.makeText(this@Resetpassword, "Password reset successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Resetpassword, Login::class.java))
                    finish()
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to reset password"
                    findViewById<TextView>(R.id.ResetError).apply {
                        text = errorMsg
                        visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                findViewById<TextView>(R.id.ResetError).apply {
                    text = "Network error: ${t.message}"
                    visibility = View.VISIBLE
                }
            }
        })
    }
}
