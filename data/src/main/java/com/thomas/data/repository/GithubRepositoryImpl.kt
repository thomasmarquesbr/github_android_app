package com.thomas.data.repository

import com.thomas.data.mappers.toDomain
import com.thomas.domain.GithubRepository
import com.thomas.domain.model.UserDetailsModel
import com.thomas.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GithubRepositoryImpl(
    private val apiDataSource: GithubAPIDataSource
): GithubRepository {
    override suspend fun getUsers(): Flow<List<UserModel>> {
        return apiDataSource.getUsers()
            .map { it.toDomain() }
    }

    override suspend fun getUserDetails(username: String): Flow<UserDetailsModel> {
        return apiDataSource.getUserDetails(username)
            .map { it.toDomain() }
    }
}