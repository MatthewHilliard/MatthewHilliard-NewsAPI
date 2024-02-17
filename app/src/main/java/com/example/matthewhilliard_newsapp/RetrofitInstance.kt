package com.example.matthewhilliard_newsapp

import retrofit2.Retrofit
import com.example.matthewhilliard_newsapp.Constants.Companion.BASE_URL
import retrofit2.converter.moshi.MoshiConverterFactory

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

internal val api: NewsAPI = retrofit.create(NewsAPI::class.java)