package com.example.test.login_activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.models.OTPResponse
import com.example.test.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.test.R

class OTP : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var otpInputs: List<EditText>
    private lateinit var errorText: TextView
    private lateinit var resendOtpText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_otp_code)

        email = intent.getStringExtra("email") ?: ""

        otpInputs = listOf(
            findViewById(R.id.otp_digit1),
            findViewById(R.id.otp_digit2),
            findViewById(R.id.otp_digit3),
            findViewById(R.id.otp_digit4),
            findViewById(R.id.otp_digit5),
            findViewById(R.id.otp_digit6)
        )

        errorText = findViewById(R.id.otpError)
        resendOtpText = findViewById(R.id.resend_otp_text)

        setupOtpInputs()

        resendOtpText.setOnClickListener {
            resendOTP()
        }
    }

    private fun setupOtpInputs() {
        for (i in otpInputs.indices) {
            otpInputs[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpInputs.size - 1) {
                        otpInputs[i + 1].requestFocus()
                    } else if (s?.isEmpty() == true && i > 0) {
                        otpInputs[i - 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    val otp = otpInputs.joinToString("") { it.text.toString() }
                    if (otp.length == 6) {
                        verifyOTP(email, otp)
                    }
                }
            })
        }
    }

    private fun verifyOTP(email: String, otp: String) {
        RetrofitClient.instance.verifyOTP(email, otp).enqueue(object : Callback<OTPResponse> {
            override fun onResponse(call: Call<OTPResponse>, response: Response<OTPResponse>) {
                if (response.body()?.success == true) {
                    Toast.makeText(applicationContext, "OTP verified successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@OTP, Resetpassword::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()
                } else {
                    errorText.text = response.body()?.message ?: "Invalid or expired OTP"
                    errorText.visibility = TextView.VISIBLE
                    clearOtpInputs()
                }
            }

            override fun onFailure(call: Call<OTPResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Verification failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun clearOtpInputs() {
        for (editText in otpInputs) {
            editText.text.clear()
        }
        otpInputs[0].requestFocus()
    }

    private fun resendOTP() {
        Toast.makeText(this, "Resending OTP...", Toast.LENGTH_SHORT).show()
        // Implement API call to resend OTP here
    }
}
