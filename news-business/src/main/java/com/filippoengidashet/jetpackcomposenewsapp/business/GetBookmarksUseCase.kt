package com.filippoengidashet.jetpackcomposenewsapp.business

import com.filippoengidashet.jetpackcomposenewsapp.data.NewsRepository
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {

    suspend fun fetchBookmarks() = newsRepository.getBookmarks()
}
