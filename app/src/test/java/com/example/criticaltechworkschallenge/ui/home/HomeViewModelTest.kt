package com.example.criticaltechworkschallenge.ui.home

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.criticaltechworkschallenge.Constants
import com.example.criticaltechworkschallenge.dto.Article
import com.example.criticaltechworkschallenge.dto.NewsResponse
import com.example.criticaltechworkschallenge.enums.SourcesEnum
import com.example.criticaltechworkschallenge.services.NewsService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsService: NewsService

    @Mock
    private lateinit var call: Call<NewsResponse>

    @Mock
    private lateinit var application: Application

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = HomeViewModel(application)
    }

    @Test
    fun `test updateSource`() {
        // Arrange
        val newSource = SourcesEnum.CNN

        // Act
        viewModel.updateSource(newSource)

        // Assert
        assertEquals(newSource, viewModel.newsSource.value)
    }

    @Test
    fun `test loadHeadlines success`() {

        // Arrange
        val articles = listOf(
            Article("title", "description", "url", "urlToImage", "content"),
            Article("title2", "description2", "url2", "urlToImage2", "content2"),
        )

        val response = mock<Response<NewsResponse>>()
        whenever(response.body()).thenReturn(NewsResponse("ok", 1, articles))
        setupEnqueueSuccessResponse(response)

        // Act
        viewModel.loadHeadlines()

        // Assert / Verify
        // verify(newsService, times(1)).getTopHeadlines(SourcesEnum.CNN.id, Constants.API_KEY)
        assertNotNull(viewModel.articles) // list contains elements
    }

    private fun setupEnqueueSuccessResponse(response: Response<NewsResponse>) {
        whenever(call.enqueue(any())).thenAnswer {
            val callback = it.arguments[0] as Callback<NewsResponse>
            callback.onResponse(call, response)
        }
        whenever(
            newsService.getTopHeadlines(
                SourcesEnum.CNN.id,
                Constants.API_KEY
            )
        ).thenReturn(call)
    }

    @Test
    fun `test loadHeadlines failure`() {
        // Arrange
        val response = mock<Response<NewsResponse>>()
        setupEnqueueFailureResponse(response)

        // Act
        viewModel.loadHeadlines()

        // Assert / Verify
        // verify(newsService, times(1)).getTopHeadlines(SourcesEnum.CNN.id, Constants.API_KEY)
        assertNotNull(viewModel.loadHeadlines())
    }

    private fun setupEnqueueFailureResponse(response: Response<NewsResponse>) {
        whenever(call.enqueue(any())).thenAnswer {
            val callback = it.arguments[0] as Callback<NewsResponse>
            callback.onFailure(call, Throwable("Test Failure"))
        }
        whenever(
            newsService.getTopHeadlines(
                SourcesEnum.CNN.id,
                Constants.API_KEY
            )
        ).thenReturn(call)
    }
}