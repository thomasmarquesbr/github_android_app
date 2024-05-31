package com.thomas.data.repository

import com.thomas.data.model.RepositoryResponse
import com.thomas.data.model.UserDetailResponse
import com.thomas.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubServiceAPI {
    @GET("/users")
    suspend fun getUsers(): List<UserResponse>

    @GET("/users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): UserDetailResponse

    @GET("/users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") username: String
    ): List<RepositoryResponse>
}