package com.filippoengidashet.jetpackcomposenewsapp.business

import com.filippoengidashet.jetpackcomposenewsapp.data.NewsRepository
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ArticleData
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {

    fun searchNews(query: String): Flow<ResultWrapper<List<ArticleData>>> {
        return newsRepository.searchNews(query)
    }
}
