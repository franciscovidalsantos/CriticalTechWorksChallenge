package com.example.criticaltechworkschallenge

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}

data class NewsResponse(val articles: List<Article>)
data class Article(val title: String, val publishedAt: String, val urlToImage: String?)