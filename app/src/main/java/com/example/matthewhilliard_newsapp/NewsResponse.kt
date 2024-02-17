package com.example.matthewhilliard_newsapp

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)