package com.filippoengidashet.jetpackcomposenewsapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleInfo(
    val id: String = "",
    val title: String = "Title",
    val body: String = "Description",
    val published: String = "",
    val author: String = "",
    val url: String = "https://www.theguardian.com/technology/2025/jan/12/online-safety-laws-uk-science-minister-peter-kyle",
    val thumbnail: String = "https://media.guim.co.uk/6090d2b20ceaa41270428f7146cfc68cf3892dc3/0_0_3260_1955/500.jpg",
) : Parcelable
