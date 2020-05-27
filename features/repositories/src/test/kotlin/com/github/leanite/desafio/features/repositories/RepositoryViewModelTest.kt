package com.github.leanite.desafio.features.repositories

import android.app.Application
import com.github.leanite.data.interaction.GetRepositoriesInteractor
import com.github.leanite.model.Repository
import com.github.leanite.test.instantLiveDataAndCoroutineRules
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryViewModelTest {

    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private val interactor = mockkClass(GetRepositoriesInteractor::class)
    private val application = mockkClass(Application::class)

    private lateinit var viewModel: RepositoryViewModel

    @Before
    fun setup() {
        viewModel = RepositoryViewModel(interactor, application)
    }

    @Test
    fun whenLoadNewsReturnSuccess_verifySuccessObserverIsChanged() = runBlocking {
        val repositories = listOf<Repository>(mockk())
        val pageNumber = 0
        var count = 0

        coEvery { interactor.execute(pageNumber) } returns repositories

        viewModel.viewGetRepositoriesEvent.observeForever { event ->
            when(event) {
                is GetRepositoriesViewEvent.Loading -> count += 1
                is GetRepositoriesViewEvent.Success -> count += 2
                is GetRepositoriesViewEvent.Error -> count += 3
            }
        }

        viewModel.getRepositories(pageNumber)

        assertEquals(count, 4)
    }
}