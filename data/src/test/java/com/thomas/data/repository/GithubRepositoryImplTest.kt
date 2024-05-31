package com.thomas.data.repository

import app.cash.turbine.test
import com.thomas.data.RepositoriesStubs
import com.thomas.data.UserDetailsStubs
import com.thomas.data.UsersStubs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class GithubRepositoryImplTest {
    private val apiDataSource: GithubAPIDataSource = mockk()

    @Test
    fun `When getUsers is invoked Should return correct object`() = runBlocking {
        // Given
        val fakeList = UsersStubs.listUsersResponse
        val expectItem = UsersStubs.listUsersModel
        coEvery { apiDataSource.getUsers() } returns flowOf(fakeList)

        // When
        val result = GithubRepositoryImpl(apiDataSource).getUsers()

        // Then
        coVerify { apiDataSource.getUsers() }
        result.test {
            assertEquals(expectItem(), expectItem)
            expectComplete()
        }
    }

    @Test
    fun `When getUserDetails is invoked Should return correct object`() = runBlocking {
        // Given
        val username = "username"
        val fakeResponse = UserDetailsStubs.response
        val expectItem = UserDetailsStubs.model
        coEvery { apiDataSource.getUserDetails(username) } returns flowOf(fakeResponse)

        // When
        val result = GithubRepositoryImpl(apiDataSource).getUserDetails(username)

        // Then
        coVerify { apiDataSource.getUserDetails(username) }
        result.test {
            assertEquals(expectItem(), expectItem)
            expectComplete()
        }
    }

    @Test
    fun `When getRepositories is invoked Should return correct object`() = runBlocking {
        // Given
        val username = "username"
        val fakeResponse = RepositoriesStubs.listResponse
        val expectItem = RepositoriesStubs.listModel
        coEvery { apiDataSource.getRepositories(username) } returns flowOf(fakeResponse)

        // When
        val result = GithubRepositoryImpl(apiDataSource).getRepositories(username)

        // Then
        coVerify { apiDataSource.getRepositories(username) }
        result.test {
            assertEquals(expectItem(), expectItem)
            expectComplete()
        }
    }
}