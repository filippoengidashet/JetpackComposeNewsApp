package com.filippoengidashet.jetpackcomposenewsapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleInfo(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val published: String = "",
    val author: String = "",
    val url: String = "",
    val thumbnail: String = "",
) : Parcelable
