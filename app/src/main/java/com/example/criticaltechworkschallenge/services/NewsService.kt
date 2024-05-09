package com.example.criticaltechworkschallenge.services

import com.example.criticaltechworkschallenge.dto.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int = 20, // Default page size received from source "v2/top-headlines" is 10 // set as 20 for new source case
    ): Call<NewsResponse>
}

