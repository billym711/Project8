package com.example.project8

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.project8.databinding.RestaurantItemBinding
import com.example.project8.model.YelpRestaurant
import com.example.project8.model.YelpSearchResult


class RestaurantsAdapter(val context: Context) :
    ListAdapter<YelpSearchResult, RestaurantsAdapter.ItemViewHolder>(RestaurantDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ItemViewHolder = ItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context)
    }

    class ItemViewHolder(val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RestaurantItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }

        fun bind(restaurant: YelpSearchResult, context: Context) {
            binding.tvName.text = restaurant.title
            binding.tvNumReviews.text = "Release Date: ${restaurant.year}"
            binding.tvAddress.text = "${restaurant.runtime} runtime"
            binding.tvDistance.text = restaurant.genre
            binding.tvCategory.text = "IMDb rating: ${restaurant.imdbScore} "
            binding.tvPrice.text = "Rated ${restaurant.rating}"
            binding.tvLink.text = restaurant.imdbID
            Glide.with(context).load(restaurant.imageUrl).into(binding.imageView)
            Glide.with(context).load(restaurant.imageUrl)
                .apply(
                    RequestOptions().transform(
                        CenterCrop(), RoundedCorners(5)
                    )
               )
                .into(binding.imageView)


        }


    }
}