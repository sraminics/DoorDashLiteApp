package com.android.doordashlite

import androidx.constraintlayout.utils.widget.MockView
import com.android.doordashlite.models.Restaurant
import com.android.doordashlite.models.Status
import junit.framework.Assert.assertEquals
import org.junit.Test

class RestaurantsViewModelTest {


    private var restaurantsViewModel = RestaurantsViewModel()

    @Test
    fun process_restaurants_when_empty() {
        restaurantsViewModel.processRestaurantData(mutableListOf<Restaurant>() as ArrayList<Restaurant>)
        assertEquals(0, restaurantsViewModel.restaurantsList.size)
    }

    @Test
    fun process_restaurants_has_data_status_empty_time() {
        var listToProcess = arrayListOf<Restaurant>()
        listToProcess.add(Restaurant("9999", "TEST", "TEST DESCRIPTION", Status(mutableListOf()), "url", 24.0))
        restaurantsViewModel.processRestaurantData(listToProcess)
        assertEquals("closed", restaurantsViewModel.restaurantsList[0].time)
    }

    @Test
    fun process_restaurants_has_data_status_has_time() {
        var listToProcess = arrayListOf<Restaurant>()
        var time = arrayListOf<Int>()
        time.add(20)
        time.add(25)
        listToProcess.add(Restaurant("9999", "TEST", "TEST DESCRIPTION", Status(time), "url", 24.0))
        restaurantsViewModel.processRestaurantData(listToProcess)
        assertEquals("20", restaurantsViewModel.restaurantsList[0].time)
        assertEquals(listToProcess.size, restaurantsViewModel.restaurantsList.size)
    }
}