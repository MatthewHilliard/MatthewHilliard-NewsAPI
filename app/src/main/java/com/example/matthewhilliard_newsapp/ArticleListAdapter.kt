package com.example.matthewhilliard_newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleListAdapter(private val articles: MutableList<Article>) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_article, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val articleTitle: TextView = itemView.findViewById(R.id.article_title)
        private val articleDescription: TextView = itemView.findViewById(R.id.article_description)

        fun bind(article: Article) {
            articleTitle.text = article.title
            articleDescription.text = article.description
        }
    }
}