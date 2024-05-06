package com.example.criticaltechworkschallenge

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int = 20,
    ): Call<NewsResponse>
}

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source, // not being used
    val author: String, // not being used
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String, // not being used
    val content: String
)

class Source {
    val id: String? = null
    val name: String? = null
}
