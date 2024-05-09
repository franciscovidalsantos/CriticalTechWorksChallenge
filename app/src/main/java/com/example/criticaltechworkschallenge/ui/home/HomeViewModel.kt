package com.example.criticaltechworkschallenge.ui.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.criticaltechworkschallenge.Constants
import com.example.criticaltechworkschallenge.dto.Article
import com.example.criticaltechworkschallenge.dto.NewsResponse
import com.example.criticaltechworkschallenge.enums.SourcesEnum
import com.example.criticaltechworkschallenge.services.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _buttonText = MutableLiveData<String>().apply {
        value = "Click to select a new source"
    }
    val buttonText: LiveData<String> = _buttonText

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _newsSource = MutableLiveData<SourcesEnum>().apply {
        value = SourcesEnum.BBC_NEWS // default source
    }
    val newsSource: LiveData<SourcesEnum> = _newsSource

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val newsService: NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }

    fun updateSource(newSource: SourcesEnum) {
        _newsSource.value = newSource
        loadHeadlines()
    }

    init {
        loadHeadlines()
    }

    fun loadHeadlines() {

        val currentSource = newsSource.value?.id.toString()

        // API Call
        val call = newsService.getTopHeadlines(
            currentSource,
            Constants.API_KEY
        )

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    _articles.postValue(articles)
                } else {
                    // Handle error
                     Toast.makeText(getApplication(), "Failed to load headlines: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle failure
                 Toast.makeText(getApplication(), "Failed to load headlines: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}