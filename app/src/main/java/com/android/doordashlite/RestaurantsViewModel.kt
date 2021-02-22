package com.android.doordashlite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.doordashlite.models.Restaurant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.math.RoundingMode
import java.text.DecimalFormat

class RestaurantsViewModel: ViewModel() {

    private val restaurantsInteraction = RestaurantsInteractor()
    private val compositeDisposable = CompositeDisposable()
    lateinit var restaurantsList: ArrayList<RestaurantRowData>
    private var _restaurants = MutableLiveData<ArrayList<RestaurantRowData>>()


    fun fetchRestaurants() {
        compositeDisposable.add(restaurantsInteraction.getRestaurants(lat = 37.422740, long = -122.139956)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                processRestaurantData(it.stores)
                _restaurants.value = restaurantsList
            },{ throwable: Throwable ->
                _restaurants.value = null
                Log.e("Fetching Error", throwable.toString()) }))
    }

    fun getRestaurants(): LiveData<ArrayList<RestaurantRowData>> {
        return _restaurants
    }

    fun processRestaurantData(restaurantList: ArrayList<Restaurant>) {
        var processedData = mutableListOf<RestaurantRowData>()
        restaurantList.forEach {
            var time = "closed"
            if(!it.status.deliveryTime.isNullOrEmpty()) {
                time = it.status.deliveryTime[0].toString()
            }
            processedData.add(RestaurantRowData(it.id, it.name, it.description, it.imageUrl, time))
        }
        restaurantsList = processedData as ArrayList<RestaurantRowData>
    }

    private fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

}

data class RestaurantRowData(val id: String, val name: String, val description: String, val imageUrl: String, val time: String)