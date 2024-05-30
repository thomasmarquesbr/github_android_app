package com.thomas.data.repository

import com.thomas.data.mappers.toDomain
import com.thomas.domain.GithubRepository
import com.thomas.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GithubRepositoryImpl(
    private val apiDataSource: GithubAPIDataSource
): GithubRepository {
    override suspend fun getUsers(): Flow<List<UserModel>> {
        return apiDataSource.getEvents()
            .map { it.toDomain() }
    }
}