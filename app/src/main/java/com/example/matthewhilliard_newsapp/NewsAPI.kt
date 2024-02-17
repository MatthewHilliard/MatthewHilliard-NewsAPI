package com.example.matthewhilliard_newsapp

import com.example.matthewhilliard_newsapp.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import com.example.matthewhilliard_newsapp.Constants.Companion.API_KEY
import retrofit2.Call

interface NewsAPI {
    @GET("v2/top-headlines")
    fun getHeadlines(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Call<NewsResponse>

    @GET("v2/everything")
    fun getEverything(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Call<NewsResponse>
}