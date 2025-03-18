package com.example.test.login_activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.network.RetrofitClient
import com.example.test.network.ForgotPasswordResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.test.R


class Forgot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_forgot)

        val forgotButton = findViewById<Button>(R.id.ForgotButton)
        val forgotEmail = findViewById<EditText>(R.id.ForgotEmail)

        forgotButton.setOnClickListener {
            val email = forgotEmail.text.toString().trim()
            if (email.isNotEmpty()) {
                RetrofitClient.instance.forgotPassword(email)
                    .enqueue(object : Callback<ForgotPasswordResponse> {
                        override fun onResponse(
                            call: Call<ForgotPasswordResponse>,
                            response: Response<ForgotPasswordResponse>
                        ) {
                            if (response.body()?.success == true) {
                                // Navigate to ResendEmail screen
                                val intent = Intent(this@Forgot, OTP::class.java)
                                intent.putExtra("email", email)
                                startActivity(intent)
                                finish() // This prevents going back to Login
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()?.message ?: "Something went wrong",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, "Request failed", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {
                Toast.makeText(applicationContext, "Enter your email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
