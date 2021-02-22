package com.android.doordashlite.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.doordashlite.R
import com.android.doordashlite.RestaurantRowData
import com.android.doordashlite.models.Restaurant
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_row.view.*


class RestaurantAdapter(val restaurants: ArrayList<RestaurantRowData>, val context: Context, val rowTappedListener: (String) -> Unit) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.restaurant_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.nameTextView.text = restaurant.name
        holder.typeTextView.text = restaurant.description
        holder.distanceTextView.text = String.format(context.getString(R.string.time), restaurant.time)

        Glide.with(context)
            .load(restaurant.imageUrl)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            rowTappedListener.invoke(restaurant.id)
        }

    }

   class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.image
        val nameTextView: TextView = view.restaurant_name
        val typeTextView: TextView = view.restaurant_type
        val distanceTextView: TextView = view.restaurant_distance
    }
}