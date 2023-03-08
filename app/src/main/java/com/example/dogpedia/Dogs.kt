package com.example.dogpedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dogs(
    val name: String,
    val description: String,
    val otherName: String,
    val origin: String,
    val photo: Int
) : Parcelable
