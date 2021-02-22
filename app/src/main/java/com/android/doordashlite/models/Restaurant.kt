package com.android.doordashlite.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(val id: String,
                 val name: String,
                 val description: String,
                 val status: Status,
                 @SerializedName("cover_img_url") val imageUrl: String,
                 @SerializedName("distance_from_consumer") val distance: Double): Parcelable


