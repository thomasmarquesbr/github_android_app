package com.thomas.data

import com.thomas.data.model.UserResponse
import com.thomas.domain.model.UserModel

internal object UsersStubs {
    val listUsersModel = listOf(
        UserModel(
            nickname = "nickname1",
            avatarUrl = "https://avatar1.url"
        ),
        UserModel(
            nickname = "nickname2",
            avatarUrl = "https://avatar2.url"
        )
    )

    val listUsersResponse = listOf(
        UserResponse(
            login = "nickname1",
            avatarUrl = "https://avatar1.url"
        ),
        UserResponse(
            login = "nickname2",
            avatarUrl = "https://avatar2.url"
        )
    )
}