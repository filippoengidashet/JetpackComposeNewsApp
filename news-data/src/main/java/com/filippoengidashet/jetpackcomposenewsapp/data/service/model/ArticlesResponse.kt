package com.filippoengidashet.jetpackcomposenewsapp.data.service.model

data class ArticlesResponse(
    val orderBy: String? = null,
    val currentPage: Int? = null,
    val pageSize: Int? = null,
    val pages: Int? = null,
    val results: List<ArticleResult>? = null,
    val startIndex: Int? = null,
    val total: Int? = null,
    val status: String? = null,
    val userTier: String? = null,
)