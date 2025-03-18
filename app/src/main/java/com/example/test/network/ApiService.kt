package com.example.test.network

import com.example.test.models.OTPResponse
import com.example.test.models.ResponseModel
import retrofit2.Call
import retrofit2.http.*

data class ForgotPasswordResponse(val success: Boolean, val message: String)
data class ApiResponse(val success: Boolean, val message: String)

interface ApiService {
    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(
        @Field("full_name") fullName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseModel>

    @FormUrlEncoded
    @POST("forgot_password.php")
    fun forgotPassword(
        @Field("email") email: String
    ): Call<ForgotPasswordResponse>

    @FormUrlEncoded
    @POST("resend_otp.php") // API to resend OTP
    fun resendOtp(
        @Field("email") email: String
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("verify_otp.php") // API to verify OTP
    fun verifyOTP(
        @Field("email") email: String,
        @Field("otp") otp: String
    ): Call<OTPResponse>

    @FormUrlEncoded
    @POST("reset_password.php")
    fun resetPassword(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseModel>
}
