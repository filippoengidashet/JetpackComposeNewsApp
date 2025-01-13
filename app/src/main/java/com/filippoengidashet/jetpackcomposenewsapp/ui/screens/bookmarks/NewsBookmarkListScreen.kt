package com.filippoengidashet.jetpackcomposenewsapp.ui.screens.bookmarks

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.filippoengidashet.jetpackcomposenewsapp.ui.components.TopAppBarNavComponent

@Composable
fun NewsBookmarkListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBarNavComponent(title = "Bookmarked Articles") {
                navController.popBackStack()
            }
        }) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .animateContentSize()
        ) {
            Text(text = "TODO: Article Bookmark List Screen!")
        }
    }
}
