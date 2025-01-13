package com.filippoengidashet.jetpackcomposenewsapp.data.source

import com.filippoengidashet.jetpackcomposenewsapp.data.service.NewsApiService
import javax.inject.Inject

class RemoteNewsDataSource @Inject constructor(
    private val newsApiService: NewsApiService,
) {

    suspend fun fetchHeadlineArticles() = newsApiService.fetchHeadlineArticles()

    suspend fun searchNews(query: String) = newsApiService.searchNews(query)
}
