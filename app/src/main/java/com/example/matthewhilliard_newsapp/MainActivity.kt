package com.example.matthewhilliard_newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ArticleListAdapter
    private lateinit var articleRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        articleRecyclerView = findViewById(R.id.article_recycler_view)
        layoutManager = LinearLayoutManager(this)
        articleRecyclerView.layoutManager = layoutManager

        fetchArticles()
    }

    private fun fetchArticles() {
        val call: Call<NewsResponse> = api.getHeadlines()
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    updateAdapter(articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("MainActivity", "Failed to fetch articles")
            }
        })
    }

    private fun updateAdapter(articles: List<Article>) {
        adapter = ArticleListAdapter(articles.toMutableList())
        articleRecyclerView.adapter = adapter
    }
}