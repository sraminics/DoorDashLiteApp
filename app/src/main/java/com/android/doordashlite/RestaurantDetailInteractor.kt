package com.android.doordashlite

import com.android.doordashlite.API.DoorDashAPI
import com.android.doordashlite.models.Restaurant
import com.android.doordashlite.models.RestaurantDetails
import com.android.doordashlite.models.RestaurantResponse
import io.reactivex.Single

class RestaurantDetailInteractor {

    fun getRestaurantDetail(restaurantId: String?): Single<RestaurantDetails> {
        return DoorDashAPI.service.getRestaurantDetails(restaurantId)
    }
}