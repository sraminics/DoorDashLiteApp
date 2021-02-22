package com.android.doordashlite.API

import com.android.doordashlite.models.Restaurant
import com.android.doordashlite.models.RestaurantDetails
import com.android.doordashlite.models.RestaurantResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.*
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class DoorDashAPI {

    interface APIService {

        @GET("/v1/store_feed/")
        fun getRestaurants(@Query("lat") lat: Double,
                           @Query("lng")long: Double,
                           @Query("offset") offset: Int? = 0,
                           @Query("limit") limit: Int? = 50): Single<RestaurantResponse>

        @GET("/v2/restaurant/{restaurant_id}/")
        fun getRestaurantDetails(@Path("restaurant_id") id: String?): Single<RestaurantDetails>

    }


    companion object {
        var client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build()

        private val retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://api.doordash.com").client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(create())
                .build()

        var service = retrofit.create(
            APIService::class.java)
    }
}