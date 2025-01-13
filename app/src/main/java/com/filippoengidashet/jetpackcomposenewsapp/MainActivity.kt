package com.filippoengidashet.jetpackcomposenewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.ArticleInfo
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.NavRouteType
import com.filippoengidashet.jetpackcomposenewsapp.ui.screens.bookmarks.NewsBookmarkListScreen
import com.filippoengidashet.jetpackcomposenewsapp.ui.screens.detail.NewsDetailScreen
import com.filippoengidashet.jetpackcomposenewsapp.ui.screens.headlines.NewsHeadlineListScreen
import com.filippoengidashet.jetpackcomposenewsapp.ui.theme.JetpackComposeNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            JetpackComposeNewsAppTheme {
                NewsAppNavGraph(modifier = Modifier.padding())
            }
        }
    }
}

@Composable
fun NewsAppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRouteType.NEWS_HEADLINES.name,
    ) {
        composable(route = NavRouteType.NEWS_HEADLINES.name) {
            NewsHeadlineListScreen(navController)
        }
        composable(route = NavRouteType.NEWS_DETAIL.name) {
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<ArticleInfo>(
                NavRouteType.NEWS_DETAIL.name
            )
            article?.let {
                NewsDetailScreen(navController, it)
            }
        }
        composable(route = NavRouteType.NEWS_BOOKMARKS.name) {
            NewsBookmarkListScreen(navController)
        }
    }
}
