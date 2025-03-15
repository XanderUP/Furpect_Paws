package com.example.test.services

data class ServicesItems(
    val imageRes: Int,
    val title: String,  // ✅ Matches `serviceTitle` in `ServicesAdapter`
    val description: String  // ✅ Matches `serviceDescription`
)
