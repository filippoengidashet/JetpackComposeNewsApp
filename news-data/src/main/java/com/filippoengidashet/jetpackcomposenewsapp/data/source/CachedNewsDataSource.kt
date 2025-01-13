package com.filippoengidashet.jetpackcomposenewsapp.data.source

import com.filippoengidashet.jetpackcomposenewsapp.data.model.ArticleData
import com.filippoengidashet.jetpackcomposenewsapp.data.model.toEntity
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.NewsAppDatabase
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.NewsBookmarkEntity
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.toDomain
import javax.inject.Inject

class CachedNewsDataSource @Inject constructor(
    private val database: NewsAppDatabase,
) {
    //cached articles
    suspend fun getArticles(): List<ArticleData> {
        return database.newsArticleDao().getAll().map {
            it.toDomain()
        }
    }

    suspend fun saveArticles(results: List<ArticleData>) {
        val newsArticleDao = database.newsArticleDao()
        newsArticleDao.deleteAll()
        for (result in results) {
            newsArticleDao.insert(result.toEntity())
        }
    }

    //saved bookmarks
    suspend fun getBookmarks(): List<ArticleData> {
        return database.newsBookmarkDao().getAll().map {
            it.toDomain()
        }
    }

    suspend fun saveBookmark(item: ArticleData) {
        val entity = NewsBookmarkEntity(
            id = item.id,
            title = item.title,
            body = item.body,
            author = item.author,
            url = item.url,
            thumbnail = item.thumbnail,
            published = item.published
        )
        database.newsBookmarkDao().insert(entity)
    }

    suspend fun removeBookmark(id: String) {
        database.newsBookmarkDao().deleteById(id)
    }

    suspend fun deleteBookmarks() {
        database.newsBookmarkDao().deleteAll()
    }
}
