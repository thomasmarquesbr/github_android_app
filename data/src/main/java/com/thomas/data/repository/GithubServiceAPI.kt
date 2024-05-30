package com.thomas.data.repository

import com.thomas.data.model.UserResponse
import retrofit2.http.GET

internal interface GithubServiceAPI {
    @GET("/users")
    suspend fun getUsers(): List<UserResponse>
}