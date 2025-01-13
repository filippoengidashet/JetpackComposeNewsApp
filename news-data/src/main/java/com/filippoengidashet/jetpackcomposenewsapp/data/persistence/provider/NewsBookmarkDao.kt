package com.filippoengidashet.jetpackcomposenewsapp.data.persistence.provider

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model.NewsBookmarkEntity

@Dao
interface NewsBookmarkDao {

    @Query("SELECT EXISTS (SELECT 1 FROM news_bookmark_table WHERE id = :id)")
    suspend fun isBookmarked(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NewsBookmarkEntity)

    @Query("SELECT * from news_bookmark_table order by id ASC")
    suspend fun getAll(): List<NewsBookmarkEntity>

    @Query("DELETE FROM news_bookmark_table WHERE id = :id")
    suspend fun deleteById(id: String)

    @Delete
    suspend fun delete(entity: NewsBookmarkEntity)

    @Query("DELETE FROM news_bookmark_table")
    suspend fun deleteAll()
}
