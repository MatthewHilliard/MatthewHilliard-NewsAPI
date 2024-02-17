package com.example.matthewhilliard_newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchNewsArticles()
    }

    private fun fetchNewsArticles() {
        val call: Call<NewsResponse> = api.getHeadlines()
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val articles = response.body().articles
                articles.forEach { article ->
                    Log.d("MainActivity", "Title: ${article.title}, Description: ${article.description}")
                    }
                }
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("MainActivity", "Failed")
            }
        })
    }
}