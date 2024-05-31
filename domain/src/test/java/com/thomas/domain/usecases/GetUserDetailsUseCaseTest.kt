package com.thomas.domain.usecases

import app.cash.turbine.test
import com.thomas.domain.GithubRepository
import com.thomas.domain.UserDetailsStubs
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class GetUserDetailsUseCaseTest {
    private val repository: GithubRepository = mockk()

    @Test
    fun `When useCase is Should return correct object`() = runBlocking {
        // Given
        val username = "username"
        val expectItem = UserDetailsStubs.userDetails
        coEvery { repository.getUserDetails(username) } returns flowOf(expectItem)

        // When
        val result = GetUserDetailsUseCase(repository).invoke(username)

        // Then
        result.test {
            assertEquals(expectItem(), expectItem)
            expectComplete()
        }
    }

    @Test
    fun `When useCase is Should return a error`() = runBlocking {
        // Given
        val username = "username"
        val error = Throwable("Generic error")
        coEvery { repository.getUserDetails(username) } returns flow { throw error }

        // When
        val result = GetUserDetailsUseCase(repository).invoke(username)

        // Then
        result.test {
            assertEquals(expectError(), error)
        }
    }
}