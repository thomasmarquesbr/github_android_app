package com.thomas.data.repository

import com.thomas.data.helper.parseHttpError
import com.thomas.data.model.UserDetailResponse
import com.thomas.data.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GithubAPIDataSource(
    private val service: GithubServiceAPI
) {
    fun getUsers(): Flow<List<UserResponse>> =
        flow {
            emit(service.getUsers())
        }.parseHttpError()

    fun getUserDetails(username: String): Flow<UserDetailResponse> =
        flow {
            emit(service.getUserDetail(username))
        }.parseHttpError()
}