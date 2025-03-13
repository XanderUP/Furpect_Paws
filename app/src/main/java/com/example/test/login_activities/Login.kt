    package com.example.test.login_activities

    import android.content.Context
    import android.content.Intent
    import android.content.SharedPreferences
    import android.os.Bundle
    import android.text.SpannableString
    import android.text.Spanned
import android.text.InputType
    import android.text.method.LinkMovementMethod
    import android.text.style.ClickableSpan
    import android.text.style.UnderlineSpan
    import android.view.View
    import android.widget.*
    import androidx.appcompat.app.AppCompatActivity
    import com.example.test.R
    import com.example.test.models.ResponseModel
    import com.example.test.network.RetrofitClient
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response

    class Login : AppCompatActivity() {
        private lateinit var emailEditText: EditText
        private lateinit var passwordEditText: EditText
        private lateinit var loginButton: Button
        private lateinit var errorEmail: TextView
        private lateinit var rememberCheck: CheckBox
        private lateinit var passwordShow: CheckBox
        private lateinit var sharedPreferences: SharedPreferences

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.login)

            emailEditText = findViewById(R.id.LogInEmail)
            passwordEditText = findViewById(R.id.LogInPassword)
            loginButton = findViewById(R.id.LogInButton)
            errorEmail = findViewById(R.id.errorEmail)
            rememberCheck = findViewById(R.id.LogInRememberCheck)
            passwordShow = findViewById(R.id.LogInShowPass)

            sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

            // Check if user is already logged in
            if (sharedPreferences.getBoolean("isLoggedIn", false)) {
                navigateToHome()
            }

            loginButton.setOnClickListener {
                loginUser()
            }
            passwordShow.setOnCheckedChangeListener { _, isChecked ->
                showPassword(isChecked)
            }


            setupTextLinks()
        }
        // TEXT LINKS FUNCTION
        private fun setupTextLinks() {
            val signup = findViewById<TextView>(R.id.LogInSignup)
            val forgot = findViewById<TextView>(R.id.LogInForgotPassword)

            val signupText = "Don't have an Account? Sign Up"
            val forgotText = "Forgot Password?"

            val sStringSignup = SpannableString(signupText)
            val sStringForgot = SpannableString(forgotText)

            val startSignup = signupText.indexOf("Sign Up")
            val endSignup = startSignup + "Sign Up".length

            val startForgot = forgotText.indexOf("Forgot Password?")
            val endForgot = startForgot + "Forgot Password?".length

            sStringSignup.setSpan(UnderlineSpan(), startSignup, endSignup, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            sStringForgot.setSpan(UnderlineSpan(), startForgot, endForgot, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val clickableSpanSignup = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    startActivity(Intent(this@Login, SignupActivity::class.java))
                }
            }

            val clickableSpanForgot = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    startActivity(Intent(this@Login, Forgot::class.java))
                }
            }

            sStringSignup.setSpan(clickableSpanSignup, startSignup, endSignup, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            sStringForgot.setSpan(clickableSpanForgot, startForgot, endForgot, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            signup.text = sStringSignup
            signup.movementMethod = LinkMovementMethod.getInstance()
            forgot.text = sStringForgot
            forgot.movementMethod = LinkMovementMethod.getInstance()
        }

        // LOGIN ERROR
        private fun loginUser() {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty()) {
                errorEmail.text = "Email is required!"
                errorEmail.visibility = View.VISIBLE
                return
            }

            if (password.isEmpty()) {
                errorEmail.text = "Password is required!"
                errorEmail.visibility = View.VISIBLE
                return
            }

            // Call Retrofit API
            RetrofitClient.instance.loginUser(email, password)
                .enqueue(object : Callback<ResponseModel> {
                    override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                        if (response.isSuccessful) {
                            val res = response.body()
                            if (res != null && !res.error) {
                                // Store login session if "Remember Me" is checked
                                if (rememberCheck.isChecked) {
                                    with(sharedPreferences.edit()) {
                                        putBoolean("isLoggedIn", true)
                                        putString("userName", res.user?.full_name)
                                        putString("userEmail", res.user?.email)
                                        apply()
                                    }
                                }
                                Toast.makeText(this@Login, res.message, Toast.LENGTH_SHORT).show()
                                navigateToHome()
                            } else {
                                errorEmail.text = res?.message ?: "Login failed!"
                                errorEmail.visibility = View.VISIBLE
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        errorEmail.text = "Network error: ${t.message}"
                        errorEmail.visibility = View.VISIBLE
                    }
                })
        }

        //NAVIGATE TO HOME FUNCTION
        private fun navigateToHome() {
            startActivity(Intent(this, Welcome::class.java))
            finish()
        }

        //SHOW PASSWORD FUNCTION
        private fun showPassword(show: Boolean){
            if (show){
                passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            else{
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            passwordEditText.setSelection(passwordEditText.text.length)

        }


    }
