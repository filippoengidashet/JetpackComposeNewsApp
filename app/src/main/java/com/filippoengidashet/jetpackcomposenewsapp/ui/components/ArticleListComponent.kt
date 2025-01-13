package com.filippoengidashet.jetpackcomposenewsapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.ArticleInfo
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.NavRouteType

@Composable
fun ArticleListComponent(articles: List<ArticleInfo>, navController: NavController) {
    val size = articles.size
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(articles) { index, item ->
            ArticleItemComponent(item) {
                val routeName = NavRouteType.NEWS_DETAIL.name
                navController.currentBackStackEntry?.savedStateHandle?.set(routeName, item)
                navController.navigate(routeName)
            }
            if (index < size - 1) {
                HorizontalDivider(modifier = Modifier.padding(start = 16.dp))
            }
        }
    }
}
