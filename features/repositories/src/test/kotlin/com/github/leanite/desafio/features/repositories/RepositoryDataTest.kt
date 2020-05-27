package com.github.leanite.desafio.features.repositories

import android.os.Build
import com.github.leanite.data.api.RepositoryApi
import com.github.leanite.data.interaction.GetRepositoriesInteractor
import com.github.leanite.test.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RepositoryDataTest {

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private lateinit var repositoryApi: RepositoryApi

    private lateinit var getRepositoriesInteractor: GetRepositoriesInteractor

    @Before
    fun setup() {
        repositoryApi = RetrofitTest.provideApi(RepositoryApi::class.java)
        getRepositoriesInteractor = GetRepositoriesInteractor(repositoryApi)
    }

    @Test
    fun whenGetNewsCalledWithSuccessResponse_verifyResultIsSuccess() {
        runBlocking {
            mockWebServerRule.mockWebServer.enqueue200Response(getJson(com.github.leanite.test.R.raw.get_repositories)) //TODO: mudar package de teste para desafio tbm e ver se resolve isso

            val response = getRepositoriesInteractor.execute(0)

            assertTrue(response.isNotEmpty())
        }
    }

//    @Test
//    fun whenGetNewsCalledWithErrorResponse_verifyResultIsError() {
//        runBlocking {
//            mockWebServerRule.mockWebServer.enqueue400Response(getJson(R.raw.get_repositories))
//
//            assertTrue(repository.getNews() is Result.Error)
//        }
//    }
}