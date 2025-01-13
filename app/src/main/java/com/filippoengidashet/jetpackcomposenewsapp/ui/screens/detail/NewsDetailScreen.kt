package com.filippoengidashet.jetpackcomposenewsapp.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.filippoengidashet.jetpackcomposenewsapp.ui.components.TopAppBarNavComponent
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.ArticleInfo

@Composable
fun NewsDetailScreen(navController: NavController, item: ArticleInfo) {

    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBarNavComponent(title = "News Detail") {
                navController.popBackStack()
            }
        }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 21.sp
                )
                Spacer(Modifier.height(8.dp))
                AsyncImage(
                    modifier = Modifier
                        .height(256.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    model = item.thumbnail,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Article Image",
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = item.body,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
            Button(onClick = {
                uriHandler.openUri(item.url)
            }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Read More"
                )
            }
        }
    }
}
