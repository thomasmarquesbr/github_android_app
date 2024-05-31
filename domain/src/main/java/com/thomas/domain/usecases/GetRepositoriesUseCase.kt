package com.thomas.domain.usecases

import com.thomas.domain.GithubRepository
import com.thomas.domain.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUseCase(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(username: String): Flow<List<RepositoryModel>> {
        return repository.getRepositories(username)
    }
}