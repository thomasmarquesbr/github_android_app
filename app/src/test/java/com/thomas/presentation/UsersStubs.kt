package com.thomas.presentation

import com.thomas.domain.model.UserModel

object UsersStubs {
    val model = UserModel(
        nickname = "nickname1",
        avatarUrl = "https://avatar1.url"
    )

    val listUsers = listOf(
        UserModel(
            nickname = "nickname1",
            avatarUrl = "https://avatar1.url"
        ),
        UserModel(
            nickname = "nickname2",
            avatarUrl = "https://avatar2.url"
        ),
    )
}