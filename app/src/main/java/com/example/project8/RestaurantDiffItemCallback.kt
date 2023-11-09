package com.example.project8

import androidx.recyclerview.widget.DiffUtil
import com.example.project8.model.YelpRestaurant
import com.example.project8.model.YelpSearchResult


class RestaurantDiffItemCallback : DiffUtil.ItemCallback<YelpSearchResult>() {
    override fun areItemsTheSame(oldItem: YelpSearchResult, newItem: YelpSearchResult)
            = (oldItem.title == newItem.title)
    override fun areContentsTheSame(oldItem: YelpSearchResult, newItem: YelpSearchResult) = (oldItem == newItem)
}