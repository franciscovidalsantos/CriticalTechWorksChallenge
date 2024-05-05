package com.example.criticaltechworkschallenge.ui.newsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsDetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is newsDetails Fragment"
    }
    val text: LiveData<String> = _text

}