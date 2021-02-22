package com.android.doordashlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.doordashlite.adapters.RestaurantAdapter
import com.android.doordashlite.models.RestaurantDetails
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_detail_activity.*
import kotlinx.android.synthetic.main.restaurants_activity.*

class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var restaurantsDetailViewModel: RestaurantDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_detail_activity)
        setToolbarTitle()
        restaurantsDetailViewModel = RestaurantDetailViewModel()
        progressBar_cyclic.visibility = View.VISIBLE
        restaurantsDetailViewModel.fetchRestaurantDetails(intent.getStringExtra(RESTAURANT_ID))
        observeDetails()

    }

    private fun observeDetails() {
        restaurantsDetailViewModel.getRestaurantDetails().observe(this, Observer { details ->
            progressBar_cyclic.visibility = View.GONE
            if(details != null)
                setUpUI(details)
        })
    }

    private fun setUpUI(details: RestaurantDetails) {
        detail_name.text = details.name
        detail_phone_number.text = details.phoneNumber
        detail_tags.text = details.tags.toString()

        //set Rating bar
        ratingBar.progress = details.avgRating.toInt()
        ratingBar.visibility = View.VISIBLE

        //Load image
        Glide.with(this)
            .load(details.imageUrl)
            .into(detail_image)
    }


    private fun setToolbarTitle() {
        supportActionBar?.title = getString(R.string.detail)
    }
}