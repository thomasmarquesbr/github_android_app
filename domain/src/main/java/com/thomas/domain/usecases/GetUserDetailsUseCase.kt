package com.thomas.domain.usecases

import com.thomas.domain.GithubRepository
import com.thomas.domain.model.UserDetailsModel
import kotlinx.coroutines.flow.Flow

class GetUserDetailsUseCase(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(username: String): Flow<UserDetailsModel> =
        repository.getUserDetails(username)
}