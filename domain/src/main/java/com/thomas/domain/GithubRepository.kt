package com.thomas.domain

import com.thomas.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getUsers(): Flow<List<UserModel>>
}