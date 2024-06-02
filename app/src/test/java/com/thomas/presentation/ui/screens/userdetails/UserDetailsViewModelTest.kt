package com.thomas.presentation.ui.screens.userdetails

import com.thomas.domain.usecases.GetUserDetailsUseCase
import com.thomas.presentation.UserDetailsStubs
import com.thomas.presentation.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class UserDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val username = "username"
    private val getUserDetailsUseCase: GetUserDetailsUseCase = mockk(relaxed = true)
    private val goToRepositories: (String) -> Unit = mockk(relaxed = true)
    private val goBackClick: () -> Unit = mockk(relaxed = true)

    private lateinit var viewModel: UserDetailsViewModel

    fun setupViewModel() {
        viewModel = UserDetailsViewModel(
            username = username,
            getUserDetailsUseCase = getUserDetailsUseCase,
            goToRepositories = goToRepositories,
            goBackClick = goBackClick,
            dispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun `getUserDetails sets state to loading and then updates content`() = runTest {
        // Arrange
        val userDetails = UserDetailsStubs.model
        coEvery { getUserDetailsUseCase.invoke(username) } returns flowOf(userDetails)

        // Act
        setupViewModel()

        // Assert
        coVerify {
            getUserDetailsUseCase.invoke(username)
        }
    }

    @Test
    fun `onRepositoryClick invokes goToRepositories`() = runTest {
        setupViewModel()

        // Act
        viewModel.onRepositoryClick()

        // Assert
        coVerify { goToRepositories.invoke(username) }
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