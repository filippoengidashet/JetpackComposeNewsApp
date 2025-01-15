package com.filippoengidashet.jetpackcomposenewsapp.data

import com.filippoengidashet.jetpackcomposenewsapp.data.model.ArticleData
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ResultWrapper
import com.filippoengidashet.jetpackcomposenewsapp.data.service.model.NewsResponse
import com.filippoengidashet.jetpackcomposenewsapp.data.source.CachedNewsDataSource
import com.filippoengidashet.jetpackcomposenewsapp.data.source.RemoteNewsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteNewsDataSource,
    private val cachedDataSource: CachedNewsDataSource,
) {

    fun fetchHeadlineArticles(): Flow<ResultWrapper<List<ArticleData>>> {
        return flow {
            //1. Emit Saved Articles
            val cachedArticles = cachedDataSource.getArticles()
            emit(ResultWrapper.Success(cachedArticles))

            //2. Fetch Remote Articles
            try {
                val articles = remoteDataSource.fetchHeadlineArticles()
                val results = mapArticles(articles)

                //3. Save Fetched Articles
                storeArticles(results)

                //4. Emit Updated Articles
                emit(ResultWrapper.Success(results))
            } catch (e: Exception) {
                emit(ResultWrapper.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun searchNews(query: String): Flow<ResultWrapper<List<ArticleData>>> {
        return flow {
            try {
                val articles = remoteDataSource.searchNews(query)
                val results = mapArticles(articles)
                storeArticles(results)
                emit(ResultWrapper.Success(results))
            } catch (e: Exception) {
                emit(ResultWrapper.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun mapArticles(articles: NewsResponse) = articles.response.results.orEmpty().map {
        ArticleData(
            id = it.id.orEmpty(),
            url = it.webUrl.orEmpty(),
            published = it.webPublicationDate.orEmpty(),
            title = it.fields?.headline.orEmpty(),
            body = it.fields?.bodyText.orEmpty(),
            author = it.fields?.byline.orEmpty(),
            thumbnail = it.fields?.thumbnail.orEmpty(),
        )
    }

    private suspend fun storeArticles(results: List<ArticleData>) = withContext(Dispatchers.IO) {
        cachedDataSource.saveArticles(results)
    }

    suspend fun getBookmarks() = cachedDataSource.getBookmarks()

    suspend fun saveBookmark(item: ArticleData) {
        cachedDataSource.saveBookmark(item)
    }

    suspend fun deleteBookmarks() = cachedDataSource.deleteBookmarks()
}
