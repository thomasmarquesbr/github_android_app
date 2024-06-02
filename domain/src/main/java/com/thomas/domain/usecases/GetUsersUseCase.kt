package com.thomas.domain.usecases

import com.thomas.domain.GithubRepository
import com.thomas.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(): Flow<List<UserModel>> =
        repository.getUsers()
}