package com.filippoengidashet.jetpackcomposenewsapp.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.NewsArticleEntity
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.NewsBookmarkEntity
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.provider.NewsArticleDao
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.provider.NewsBookmarkDao

@Database(
    entities = [NewsArticleEntity::class, NewsBookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsAppDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao

    abstract fun newsBookmarkDao(): NewsBookmarkDao

    companion object {
        const val DB_NAME = "news_app_db"
    }
}
