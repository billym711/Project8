package com.example.project8.model

import com.google.gson.annotations.SerializedName

data class YelpSearchResult(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val imageUrl: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Rated") val rating: String,
    @SerializedName("imdbRating") val imdbScore: String,
    @SerializedName("imdbID") val imdbID: String



)
