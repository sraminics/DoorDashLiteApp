package com.android.doordashlite.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantResponse (val stores: ArrayList<Restaurant>): Parcelable