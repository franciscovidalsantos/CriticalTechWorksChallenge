package com.example.criticaltechworkschallenge.dto

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val content: String
)