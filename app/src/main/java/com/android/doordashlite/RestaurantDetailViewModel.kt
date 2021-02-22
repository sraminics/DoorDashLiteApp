package com.android.doordashlite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.doordashlite.models.Restaurant
import com.android.doordashlite.models.RestaurantDetails
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.math.RoundingMode
import java.text.DecimalFormat

class RestaurantDetailViewModel: ViewModel() {

    private val restaurantDetailInteraction = RestaurantDetailInteractor()
    private val compositeDisposable = CompositeDisposable()
    private var _restaurantDetails = MutableLiveData<RestaurantDetails>()


    fun fetchRestaurantDetails(restaurantId: String?) {
        compositeDisposable.add(restaurantDetailInteraction.getRestaurantDetail(restaurantId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({detail ->
                _restaurantDetails.value = detail

            },{ throwable: Throwable ->
                _restaurantDetails.value = null
                Log.e("Fetching Error", throwable.toString()) }))
    }

    fun getRestaurantDetails(): LiveData<RestaurantDetails> {
        return _restaurantDetails
    }

}

