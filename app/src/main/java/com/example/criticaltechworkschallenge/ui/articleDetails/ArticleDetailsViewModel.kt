package com.example.criticaltechworkschallenge.ui.articleDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticleDetailsViewModel : ViewModel() {

    private val _title = MutableLiveData<String>().apply {
        value = "Article Details"
    }
    val title: LiveData<String> = _title

}