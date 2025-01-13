package com.filippoengidashet.jetpackcomposenewsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.filippoengidashet.jetpackcomposenewsapp.R
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.NavRouteType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHomeComponent(navController: NavController, onSearch: (String) -> Unit) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var showBottomSheet by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = "JetNews App")
        },
        modifier = Modifier.shadow(
            elevation = 5.dp,
            spotColor = Color.DarkGray
        ),
        navigationIcon = {
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = "News App - Home",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = {
                showBottomSheet = true
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
            IconButton(onClick = {
                navController.navigate(NavRouteType.NEWS_BOOKMARKS.name)
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_bookmarks),
                    contentDescription = "Bookmarks",
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )

    if (showBottomSheet) {
        SearchFormModalComponent { query ->
            showBottomSheet = false
            query?.let(onSearch)
        }
    }
}
