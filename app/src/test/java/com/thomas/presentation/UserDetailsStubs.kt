package com.thomas.presentation

import com.thomas.domain.model.UserDetailsModel

object UserDetailsStubs {
    val model = UserDetailsModel(
        nickname = "nickname",
        name = "name",
        company = "company",
        avatarUrl = "https://avatar.url",
        location = "location",
        followers = 10,
        following = 10,
        reposUrl = "https://repo.url"
    )
}