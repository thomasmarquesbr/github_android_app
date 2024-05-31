package com.thomas.presentation

import com.thomas.domain.model.UserDetailsModel

object UserDetailsStubs {
    val userDetails = UserDetailsModel(
        nickname = "nickname",
        name = "name",
        company = "company",
        avatarUrl = "https://avatar.url",
        location = "location",
        followers = 10,
        following = 10,
        repoUrl = "https://repo.url"
    )
}