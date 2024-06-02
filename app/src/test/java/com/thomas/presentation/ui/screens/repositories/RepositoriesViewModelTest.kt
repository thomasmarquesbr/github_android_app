package com.thomas.presentation.ui.screens.repositories

import com.thomas.domain.usecases.GetRepositoriesUseCase
import com.thomas.presentation.RepositoryStubs
import com.thomas.presentation.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class RepositoriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val username = "username"
    private val getRepositoriesUseCase: GetRepositoriesUseCase = mockk(relaxed = true)
    private val goBackClick: () -> Unit = mockk(relaxed = true)

    private lateinit var viewModel: RepositoriesViewModel

    fun setupViewModel() {
        viewModel = RepositoriesViewModel(
            username = username,
            getRepositoriesUseCase = getRepositoriesUseCase,
            goBackClick = goBackClick,
            dispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun `getUserDetails sets state to loading and then updates content`() = runTest {
        // Arrange
        val listRepositories = RepositoryStubs.listRepositories
        coEvery { getRepositoriesUseCase.invoke(username) } returns flowOf(listRepositories)

        // Act
        setupViewModel()

        // Assert
        coVerify {
            getRepositoriesUseCase.invoke(username)
        }
    }

    @Test
    fun `onBackClick invokes goBackClick`() = runTest {
        setupViewModel()

        // Act
        viewModel.onBackClick()

        // Assert
        coVerify { goBackClick.invoke() }
    }

}