package com.filippoengidashet.jetpackcomposenewsapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.filippoengidashet.jetpackcomposenewsapp.R
import com.filippoengidashet.jetpackcomposenewsapp.ui.model.ArticleInfo
import com.filippoengidashet.jetpackcomposenewsapp.ui.theme.Typography

@Composable
fun ArticleItemComponent(article: ArticleInfo, onClick: () -> Unit) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .clickable {
                onClick.invoke()
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Top)
                .size(64.dp, 56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.inverseOnSurface),
            model = article.thumbnail,
            contentScale = ContentScale.Crop,
            contentDescription = "Article Image",
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = article.title,
                style = Typography.bodyLarge,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = article.body,
                style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        IconButton({
            Toast.makeText(context, "TODO", Toast.LENGTH_SHORT).show()
        }) {
            Icon(
                painter = painterResource(R.drawable.ic_bookmark_border),
                contentDescription = ""
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ArticleItemComponentPreview(modifier: Modifier = Modifier) {
    ArticleItemComponent(ArticleInfo()) {
    }
}
