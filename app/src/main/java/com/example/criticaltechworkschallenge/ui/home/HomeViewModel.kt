package com.example.criticaltechworkschallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.criticaltechworkschallenge.Article
import com.example.criticaltechworkschallenge.Constants
import com.example.criticaltechworkschallenge.NewsResponse
import com.example.criticaltechworkschallenge.NewsService
import com.example.criticaltechworkschallenge.enums.SourcesEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {

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

    fun updateSource(newSource: SourcesEnum) {
        _newsSource.value = newSource
    }


    init {
        loadHeadlines()
    }

    fun loadHeadlines() {

        val currentSource = newsSource.value?.id.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsService::class.java)
        val call = service.getTopHeadlines(
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
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}