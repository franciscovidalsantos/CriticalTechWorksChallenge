package com.example.criticaltechworkschallenge.ui.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.criticaltechworkschallenge.Constants
import com.example.criticaltechworkschallenge.dto.Article
import com.example.criticaltechworkschallenge.enums.SourcesEnum
import com.example.criticaltechworkschallenge.services.NewsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _buttonText = MutableLiveData<String>().apply {
        value = "Click to select a new source"
    }
    val buttonText: LiveData<String> = _buttonText

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _newsSource = MutableLiveData<SourcesEnum>()
    val newsSource: LiveData<SourcesEnum> = _newsSource

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.URL).addConverterFactory(GsonConverterFactory.create())
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
        _newsSource.value = SourcesEnum.BBC_NEWS // default source
        loadHeadlines()
    }

    fun loadHeadlines() {
        val currentSource = newsSource.value?.id.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // API Call
                val response = withContext(Dispatchers.IO) {
                    newsService.getTopHeadlines(
                        currentSource, Constants.API_KEY
                    ).execute()
                }
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    _articles.postValue(articles)
                } else {
                    // Handle error
                    showToast("Failed to load headlines: ${response.message()}")
                }
            } catch (e: Exception) {
                // Handle exception
                showToast("Exception error: ${e.message}")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show()
    }
}