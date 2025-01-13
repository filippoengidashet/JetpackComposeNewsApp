package com.filippoengidashet.jetpackcomposenewsapp.data.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ArticleData

@Entity(tableName = "news_bookmark_table")
class NewsBookmarkEntity(
    @PrimaryKey(autoGenerate = true) var index: Int = 0,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "published") val published: String,
)

fun NewsBookmarkEntity.toDomain() = ArticleData(
    id = id,
    title = title,
    body = body,
    author = author,
    url = url,
    thumbnail = thumbnail,
    published = published
)
