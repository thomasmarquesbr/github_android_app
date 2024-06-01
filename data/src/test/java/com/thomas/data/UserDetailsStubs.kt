package com.thomas.data

import com.thomas.data.model.UserDetailResponse
import com.thomas.domain.model.UserDetailsModel

internal object UserDetailsStubs {
    val model = UserDetailsModel(
        nickname = "torvalds",
        name = "Linus Torvalds",
        company = "Linux Foundation",
        avatarUrl = "https://avatars.githubusercontent.com/u/1024025?v=4",
        location = "Portland, OR",
        followers = 208485,
        following = 0,
        reposUrl = "https://api.github.com/users/torvalds/repos"
    )

    val response = UserDetailResponse(
        login = "torvalds",
        id = 1024025,
        avatarUrl = "https://avatars.githubusercontent.com/u/1024025?v=4",
        htmlUrl = "https://github.com/torvalds",
        name = "Linus Torvalds",
        company = "Linux Foundation",
        location = "Portland, OR",
        followers = 208485,
        following = 0
    )
}