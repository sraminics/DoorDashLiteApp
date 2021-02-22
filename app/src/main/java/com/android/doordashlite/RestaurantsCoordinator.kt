package com.android.doordashlite

import android.app.Activity
import android.content.Intent
import android.os.Bundle

const val RESTAURANT_ID = "restaurant_id"

class RestaurantsCoordinator {

    fun openRestaurantDetail(activity: Activity, id: String) {
        val intent = Intent(activity, RestaurantDetailActivity::class.java)
        intent.putExtra(RESTAURANT_ID, id)

        activity.startActivity(intent)
    }
}