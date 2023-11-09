package com.example.project8

import com.example.project8.model.YelpSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface YelpService {
    // To get a movie based on search
    @GET("?")
    fun searchRestaurants(@Query("t") t: String?, @Query("apikey") apiKey: String?): Call<YelpSearchResult>
}