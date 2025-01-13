package com.filippoengidashet.jetpackcomposenewsapp.business

import com.filippoengidashet.jetpackcomposenewsapp.data.NewsRepository
import javax.inject.Inject

class GetHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {

    suspend fun fetchArticles() = newsRepository.fetchHeadlineArticles()
    suspend fun searchNews(query: String) = newsRepository.searchNews(query)
}
