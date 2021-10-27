package com.example.pruebaceibasoftware.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserVO(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
) : Parcelable
