package com.android.doordashlite.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantDetails(
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("average_rating") val avgRating: Float,
    @SerializedName("cover_img_url") val imageUrl: String,
    val tags: ArrayList<String>,
    val name: String
) : Parcelable
