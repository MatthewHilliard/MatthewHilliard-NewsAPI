package com.example.matthewhilliard_newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleListFragment : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ArticleListAdapter
    private lateinit var articleRecyclerView: RecyclerView
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
        articleRecyclerView = view.findViewById(R.id.article_recycler_view)
        layoutManager = LinearLayoutManager(requireContext())
        articleRecyclerView.layoutManager = layoutManager

        searchView = view.findViewById(R.id.searchView)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchArticles(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        fetchArticles()
        return view
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
                Log.d("ArticleListFragment", "Failed to fetch articles")
            }
        })
    }

    private fun searchArticles(query: String){
        val call: Call<NewsResponse> = api.getEverything(query)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    updateAdapter(articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("ArticleListFragment", "Failed to fetch articles")
            }
        })
    }

    private fun updateAdapter(articles: List<Article>) {
        adapter = ArticleListAdapter(articles.toMutableList()) { article ->
            val bundle = Bundle().apply {
                putSerializable("article", article)
            }
            val fragment = ArticleDetailFragment().apply {
                arguments = bundle
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        articleRecyclerView.adapter = adapter
    }
}