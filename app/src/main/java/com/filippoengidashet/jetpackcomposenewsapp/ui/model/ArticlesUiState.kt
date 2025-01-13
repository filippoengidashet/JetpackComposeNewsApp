package com.filippoengidashet.jetpackcomposenewsapp.ui.model

sealed class ArticlesUiState {
    data object Idle : ArticlesUiState()
    data class Results(val articles: List<ArticleInfo>) : ArticlesUiState()
}
