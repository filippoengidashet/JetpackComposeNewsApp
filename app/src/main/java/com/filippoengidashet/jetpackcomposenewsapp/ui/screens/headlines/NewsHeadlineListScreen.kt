package com.filippoengidashet.jetpackcomposenewsapp.ui.screens.headlines

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.filippoengidashet.jetpackcomposenewsapp.ui.components.ArticleListComponent
import com.filippoengidashet.jetpackcomposenewsapp.ui.components.TopAppBarHomeComponent
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.ArticlesUiState
import com.filippoengidashet.jetpackcomposenewsapp.ui.screens.NewsViewModel
import kotlinx.coroutines.launch

@Composable
fun NewsHeadlineListScreen(navController: NavController) {

    val scope = rememberCoroutineScope()

    val newsViewModel = hiltViewModel<NewsViewModel>()
    val snackBarHostState = remember { SnackbarHostState() }

    val articlesUiState = newsViewModel.articles.collectAsStateWithLifecycle().value
    val isLoading = newsViewModel.loadingState.collectAsStateWithLifecycle().value
    val errorState = newsViewModel.events.collectAsStateWithLifecycle(null)

    if (articlesUiState == ArticlesUiState.Idle && !isLoading) {
        newsViewModel.fetchHeadlines()
    }

    val articles = (articlesUiState as? ArticlesUiState.Results)?.articles ?: emptyList()

    Scaffold(
        topBar = {
            TopAppBarHomeComponent(navController) { query ->
                newsViewModel.searchNews(query)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            ArticleListComponent(articles, navController)

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(72.dp)
                        .align(Alignment.Center),
                    strokeWidth = 4.dp,
                )
            }
        }
    }

    errorState.value?.let {
        scope.launch {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = it,
                actionLabel = "Ok",
                duration = SnackbarDuration.Short
            )
            when(snackBarResult) {
                SnackbarResult.ActionPerformed -> {}
                SnackbarResult.Dismissed -> {}
            }
        }
    }
}
