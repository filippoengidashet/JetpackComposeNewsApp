package com.filippoengidashet.jetpackcomposenewsapp.data.repository

import com.filippoengidashet.jetpackcomposenewsapp.data.NewsRepository
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ArticleData
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ResultWrapper
import com.filippoengidashet.jetpackcomposenewsapp.data.service.model.ArticleResult
import com.filippoengidashet.jetpackcomposenewsapp.data.service.model.ArticlesResponse
import com.filippoengidashet.jetpackcomposenewsapp.data.service.model.NewsResponse
import com.filippoengidashet.jetpackcomposenewsapp.data.source.CachedNewsDataSource
import com.filippoengidashet.jetpackcomposenewsapp.data.source.RemoteNewsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {

    @Mock
    private lateinit var remoteNewsDataSource: RemoteNewsDataSource

    @Mock
    private lateinit var cachedNewsDataSource: CachedNewsDataSource

    private lateinit var repository: NewsRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = NewsRepository(remoteNewsDataSource, cachedNewsDataSource)
    }

    @Test
    fun `Test fetchHeadlines SUCCESS`() = runTest {
        val mockHeadlinesResponse = NewsResponse(
            ArticlesResponse(
                results = listOf(
                    ArticleResult(),
                    ArticleResult(),
                    ArticleResult(),
                ),
            )
        )
        val cachedHeadlineResults = emptyList<ArticleData>()

        Mockito.`when`(cachedNewsDataSource.getArticles()).thenReturn(cachedHeadlineResults)
        Mockito.`when`(remoteNewsDataSource.fetchHeadlineArticles())
            .thenReturn(mockHeadlinesResponse)

        val resultFlow = repository.fetchHeadlineArticles()

        resultFlow.collect { result ->
            Assert.assertNotNull(result)
            Assert.assertTrue(result is ResultWrapper.Success)
        }
    }

    @Test
    fun searchNews() {
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
