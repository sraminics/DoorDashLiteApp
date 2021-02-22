package com.android.doordashlite

import com.android.doordashlite.API.DoorDashAPI
import com.android.doordashlite.models.Restaurant
import com.android.doordashlite.models.RestaurantResponse
import io.reactivex.Single

class RestaurantsInteractor {

    fun getRestaurants(lat: Double, long: Double): Single<RestaurantResponse> {
        return DoorDashAPI.service.getRestaurants(lat, long)
    }

    fun getRestaurantDetail() {

    }
}