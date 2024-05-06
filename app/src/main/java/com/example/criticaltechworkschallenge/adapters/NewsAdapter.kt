package com.example.criticaltechworkschallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.criticaltechworkschallenge.Article
import com.example.criticaltechworkschallenge.R
import com.squareup.picasso.Picasso

class NewsAdapter(private val articles: MutableList<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var listener: OnItemClickListener? = null

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]

        holder.title.text = article.title
        Picasso.get().load(article.urlToImage).into(holder.image)


        holder.itemView.setOnClickListener {
            listener?.onItemClick(article)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateData(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(article:Article)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}