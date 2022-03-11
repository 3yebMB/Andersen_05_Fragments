package dev.m13d.fragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String
): Parcelable
