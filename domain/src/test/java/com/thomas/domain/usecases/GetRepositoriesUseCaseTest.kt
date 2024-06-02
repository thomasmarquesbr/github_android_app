package com.thomas.domain.usecases

import app.cash.turbine.test
import com.thomas.domain.GithubRepository
import com.thomas.domain.RepositoryStubs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class GetRepositoriesUseCaseTest {
    private val repository: GithubRepository = mockk()

    @Test
    fun `When useCase is Should return correct object`() = runBlocking {
        // Given
        val username = "username"
        val expectItem = RepositoryStubs.listRepositories
        coEvery { repository.getRepositories(username) } returns flowOf(expectItem)

        // When
        val result = GetRepositoriesUseCase(repository).invoke(username)

        // Then
        coVerify { repository.getRepositories(username) }
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
        coEvery { repository.getRepositories(username) } returns flow { throw error }

        // When
        val result = GetRepositoriesUseCase(repository).invoke(username)

        // Then
        coVerify { repository.getRepositories(username) }
        result.test {
            assertEquals(expectError(), error)
        }
    }
}