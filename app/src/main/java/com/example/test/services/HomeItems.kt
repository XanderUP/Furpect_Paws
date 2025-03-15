package com.example.test.services

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeItems(
    val name: String,
    val imageRes: Int,
) : Parcelable
