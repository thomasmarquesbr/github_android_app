package com.thomas.presentation

import com.thomas.domain.model.UserModel

object UserStubs {
    val user = UserModel(
        nickname = "nickname",
        avatarUrl = "https://avatar.url"
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