package com.android.doordashlite.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Status(@SerializedName("asap_minutes_range") val deliveryTime: List<Int>) :Parcelable