package com.android.doordashlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.doordashlite.adapters.RestaurantAdapter
import kotlinx.android.synthetic.main.restaurants_activity.*

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var restaurantsViewModel: RestaurantsViewModel
    private lateinit var coordinator: RestaurantsCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurants_activity)
        setToolbarTitle()
        val myDivider =
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider)?.let { myDivider.setDrawable(it) }
        restaurants_recycler_view.addItemDecoration(myDivider)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        restaurants_recycler_view.layoutManager = linearLayoutManager
        restaurantsViewModel = RestaurantsViewModel()
        coordinator = RestaurantsCoordinator()
        restaurantsViewModel.fetchRestaurants()


    }

    private fun setToolbarTitle() {
        supportActionBar?.title = getString(R.string.discover)
    }

    override fun onResume() {
        super.onResume()
        observeRestaurants()
    }

    private fun observeRestaurants() {
        restaurantsViewModel.getRestaurants().observe(this, Observer{
            if(it != null) {
                restaurants_recycler_view.adapter =
                    RestaurantAdapter(it, this) {id ->
                        coordinator.openRestaurantDetail(this, id)
                    }
            }
        } )
    }
}