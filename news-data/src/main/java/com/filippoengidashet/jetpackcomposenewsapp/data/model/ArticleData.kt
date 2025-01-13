package com.filippoengidashet.jetpackcomposenewsapp.data.model

import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.NewsArticleEntity

data class ArticleData(
    val id: String,
    val title: String,
    val body: String,
    val author: String,
    val url: String,
    val thumbnail: String,
    val published: String,
)

fun ArticleData.toEntity() = NewsArticleEntity(
    id = id,
    title = title,
    body = body,
    author = author,
    url = url,
    thumbnail = thumbnail,
    published = published
)
