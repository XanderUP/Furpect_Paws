package com.example.test.models

data class ResponseModel(
    val error: Boolean,
    val message: String,
    val user: User?
)

data class User(
    val id: Int,
    val full_name: String,
    val email: String
)

data class OTPResponse(
    val success: Boolean,
    val message: String
)