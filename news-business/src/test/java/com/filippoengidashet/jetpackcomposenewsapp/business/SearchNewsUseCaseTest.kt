package com.filippoengidashet.jetpackcomposenewsapp.business

import com.filippoengidashet.jetpackcomposenewsapp.data.NewsRepository
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ArticleData
import com.filippoengidashet.jetpackcomposenewsapp.data.model.ResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchNewsUseCaseTest {

    private val newsRepository: NewsRepository = mockk<NewsRepository>()

    private lateinit var searchNewsUseCase: SearchNewsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        searchNewsUseCase = SearchNewsUseCase(newsRepository)
    }

    @Test
    fun `Test searchNews SUCCESS`() = runTest {
        //Given ()
        val resultSuccess = ResultWrapper.Success<List<ArticleData>>(emptyList())
        coEvery { newsRepository.searchNews(any()) } returns flowOf(resultSuccess)

        //When (Action Performed)
        val resultFlow = searchNewsUseCase.searchNews(Fixture.TEST_QUERY)

        //Then (Verify the result flow)
        resultFlow.collect { result ->
            Assert.assertEquals(resultSuccess, result)
            Assert.assertTrue(result is ResultWrapper.Success)
        }
    }

    @Test
    fun `Test searchNews FAILURE`() = runTest {
        //Given ()
        val resultError = ResultWrapper.Failure(Throwable(TEST_ERROR))
        coEvery { newsRepository.searchNews(any()) } returns flowOf(resultError)

        //When (Action Performed)
        val resultFlow = searchNewsUseCase.searchNews(TEST_QUERY)

        //Then (Verify the result flow)
        resultFlow.collect { result ->
            Assert.assertEquals(resultError, result)
            Assert.assertTrue(result is ResultWrapper.Failure)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    companion object Fixture {

        private const val TEST_QUERY = "query"
        private const val TEST_ERROR = "error"
    }
}
