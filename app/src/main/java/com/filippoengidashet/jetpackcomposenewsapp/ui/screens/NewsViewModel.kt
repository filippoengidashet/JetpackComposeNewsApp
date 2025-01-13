package com.filippoengidashet.jetpackcomposenewsapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippoengidashet.jetpackcomposenewsapp.business.GetBookmarksUseCase
import com.filippoengidashet.jetpackcomposenewsapp.business.GetHeadlinesUseCase
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ResultWrapper
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.ArticleInfo
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.ArticlesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val headlinesUseCase: GetHeadlinesUseCase,
    private val bookmarksUseCase: GetBookmarksUseCase,
) : ViewModel() {

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _articles = MutableStateFlow<ArticlesUiState>(ArticlesUiState.Idle)
    val articles: StateFlow<ArticlesUiState> = _articles

    private val _events = Channel<String>()
    val events = _events.receiveAsFlow()

    fun fetchHeadlines() {
        viewModelScope.launch {
            headlinesUseCase.fetchArticles()
                .onStart {
                    _loadingState.value = true
                }
                .onCompletion {
                    _loadingState.value = false
                }
                .collectLatest { result ->

                    when (result) {
                        is ResultWrapper.Failure -> {
                            _events.send(result.error.message.orEmpty())
                        }

                        is ResultWrapper.Success -> {
                            val value = result.value
                            val results = value.map {
                                ArticleInfo(
                                    id = it.id,
                                    title = it.title,
                                    body = it.body,
                                    author = it.author,
                                    url = it.url,
                                    thumbnail = it.thumbnail,
                                    published = it.published,
                                )
                            }
                            _articles.value = ArticlesUiState.Results(articles = results)
                        }
                    }
                }
        }
    }

    fun searchNews(query: String) {
        viewModelScope.launch {
            headlinesUseCase.searchNews(query)
                .onStart {
                    _loadingState.value = true
                }
                .onCompletion {
                    _loadingState.value = false
                }
                .collectLatest { result ->

                    when (result) {
                        is ResultWrapper.Failure -> {
                            _events.send(result.error.message.orEmpty())
                        }

                        is ResultWrapper.Success -> {
                            val value = result.value
                            val results = value.map {
                                ArticleInfo(
                                    id = it.id,
                                    title = it.title,
                                    body = it.body,
                                    author = it.author,
                                    url = it.url,
                                    thumbnail = it.thumbnail,
                                    published = it.published,
                                )
                            }
                            _articles.value = ArticlesUiState.Results(articles = results)
                        }
                    }
                }
        }
    }
}
