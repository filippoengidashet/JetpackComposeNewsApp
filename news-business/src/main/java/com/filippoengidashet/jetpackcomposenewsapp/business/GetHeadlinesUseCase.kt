package com.filippoengidashet.jetpackcomposenewsapp.business

import com.filippoengidashet.jetpackcomposenewsapp.data.NewsRepository
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ArticleData
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHeadlinesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {

    suspend fun fetchArticles(): Flow<ResultWrapper<List<ArticleData>>> {
        return newsRepository.fetchHeadlineArticles()
    }

    suspend fun searchNews(query: String) = newsRepository.searchNews(query)
}
