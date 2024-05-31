package com.thomas.domain.usecases

import app.cash.turbine.test
import com.thomas.domain.GithubRepository
import com.thomas.domain.UserStubs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class GetUsersUseCaseTest {
    private val repository: GithubRepository = mockk()

    @Test
    fun `When useCase is Should return correct object`() = runBlocking {
        // Given
        val expectItem = UserStubs.listUsers
        coEvery { repository.getUsers() } returns flowOf(expectItem)

        // When
        val result = GetUsersUseCase(repository).invoke()

        // Then
        coVerify { repository.getUsers() }
        result.test {
            Assert.assertEquals(expectItem(), expectItem)
            expectComplete()
        }
    }

    @Test
    fun `When useCase is Should return a error`() = runBlocking {
        // Given
        val error = Throwable("Generic error")
        coEvery { repository.getUsers() } returns flow { throw error }

        // When
        val result = GetUsersUseCase(repository).invoke()

        // Then
        coVerify { repository.getUsers() }
        result.test {
            Assert.assertEquals(expectError(), error)
        }
    }
}