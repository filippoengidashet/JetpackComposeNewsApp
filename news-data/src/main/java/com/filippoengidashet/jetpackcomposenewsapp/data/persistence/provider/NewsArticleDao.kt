package com.filippoengidashet.jetpackcomposenewsapp.data.persistence.provider

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.NewsArticleEntity
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.NewsBookmarkEntity

@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NewsArticleEntity)

    @Insert
    fun insertAll(vararg users: NewsArticleEntity)

    @Query("SELECT * from news_article_table order by id ASC")
    suspend fun getAll(): List<NewsBookmarkEntity>

    @Query("DELETE FROM news_article_table WHERE id = :id")
    suspend fun deleteById(id: String)

    @Delete
    suspend fun delete(entity: NewsArticleEntity)

    @Query("DELETE FROM news_article_table")
    fun deleteAll()
}
