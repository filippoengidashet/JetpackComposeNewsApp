package com.filippoengidashet.jetpackcomposenewsapp.data.service

import com.filippoengidashet.jetpackcomposenewsapp.data.service.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/search?order-by=newest&show-fields=headline,thumbnail,byline,starRating,bodyText")
    suspend fun fetchHeadlineArticles(
        @Query("page-size") page: Int = 50,
    ): NewsResponse

    @GET("/search?order-by=newest&show-fields=headline,thumbnail,byline,starRating,bodyText")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page-size") page: Int = 50,
    ): NewsResponse
}
