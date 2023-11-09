package com.example.project8

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project8.model.YelpSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Sets the URL and API key
private const val BASE_URL = "https://www.omdbapi.com/"
private const val API_KEY = "910a6f1f"
class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Instantiates view and required buttons
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var searchString = findViewById<EditText>(R.id.editTextText2).text.toString()
        var searchId = ""
        var searchButton = findViewById<Button>(R.id.button)
        var linkButton = findViewById<Button>(R.id.button2)
        var shareButton = findViewById<Button>(R.id.button3)
        var feedbackButton = findViewById<Button>(R.id.button4)


        val adapter = RestaurantsAdapter(this)
        val rvRestaurants = this.findViewById<RecyclerView>(R.id.rvRestaurants)
        rvRestaurants.adapter = adapter

        //on search button click, process the API request and set the proper data
        searchButton.setOnClickListener(){
            searchString = findViewById<EditText>(R.id.editTextText2).text.toString()
            val retrofit =
                Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build()
            val yelpService = retrofit.create(YelpService::class.java)
            yelpService.searchRestaurants(searchString, "910a6f1f").enqueue(object :
                Callback<YelpSearchResult> {
                override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                    val body = response.body()
                    searchId = body!!.imdbID
                    if (body == null) {
                        Log.w(TAG, "Did not receive valid response body from Yelp API... exiting")
                        return
                    }
                    var lst = mutableListOf<YelpSearchResult>(body)
                    adapter.submitList(lst)

                }

                override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                    Log.i(TAG, "onFailure $t")
                }
            })

        }
// Sends user to proper imdb website
        linkButton.setOnClickListener(){
            val viewIntent = Intent(
                "android.intent.action.VIEW",
                Uri.parse("http://www.imdb.com/title/" + searchId)
            )
            startActivity(viewIntent)
        }
//activates Share intent
        shareButton.setOnClickListener(){
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = searchString + ": http://www.imdb.com/title/" + searchId
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Movie")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
// Activates Email intent
        feedbackButton.setOnClickListener(){

            // define Intent object with action attribute as ACTION_SEND
            val intent = Intent(Intent.ACTION_SEND)

            // add three fields to intent using putExtra function
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("bmoorekid@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")

            // set type of intent
            intent.type = "message/rfc822"

            // startActivity with intent with chooser as Email client using createChooser function
            startActivity(Intent.createChooser(intent, "Choose an Email client :"))
        }

    }
}