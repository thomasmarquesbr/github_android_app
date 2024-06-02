package com.thomas.presentation.ui.screens.users

import com.thomas.domain.usecases.GetUsersUseCase
import com.thomas.presentation.UsersStubs
import com.thomas.presentation.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class UsersViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getUsersUseCase: GetUsersUseCase = mockk(relaxed = true)
    private val goToUserDetails: (String) -> Unit = mockk(relaxed = true)

    private lateinit var viewModel: UsersViewModel

    fun setupViewModel() {
        viewModel = UsersViewModel(
            getUsersUseCase = getUsersUseCase,
            goToUserDetails = goToUserDetails,
            dispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun `getUserDetails sets state to loading and then updates content`() = runTest {
        // Arrange
        val users = UsersStubs.listUsers
        coEvery { getUsersUseCase.invoke() } returns flowOf(users)

        // Act
        setupViewModel()

        // Assert
        coVerify {
            getUsersUseCase.invoke()
        }
    }

    @Test
    fun `onRepositoryClick invokes goToRepositories`() = runTest {
        val user = UsersStubs.model
        setupViewModel()

        // Act
        viewModel.onUserItemClicked(user)

        // Assert
        coVerify { goToUserDetails.invoke(user.nickname) }
    }

    @Test
    fun `onSearchValue is called with nickname uiState Should return 1 item in filteredContent`() = runTest {
        val users = UsersStubs.listUsers
        val searchValue = users.first().nickname
        coEvery { getUsersUseCase.invoke() } returns flowOf(users)
        setupViewModel()

        // Act
        viewModel.onSearchValue(searchValue)

        // Assert
        assertEquals(viewModel.uiState.value.filteredContent.size, 1)
        assertEquals(viewModel.uiState.value.filteredContent.first(), users.first())
    }
}