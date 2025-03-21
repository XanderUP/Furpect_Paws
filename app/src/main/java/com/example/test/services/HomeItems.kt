package com.example.test.services

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeItems(
    val title: String,// ✅ Matches `serviceTitle` in `ServicesAdapter`
    val imageRes: Int,
    val description: String  // ✅ Matches `serviceDescription`
) : Parcelable
