package com.thomas.data.mappers

import com.thomas.data.model.UserDetailResponse
import com.thomas.domain.model.UserDetailsModel

internal fun UserDetailResponse.toDomain() = UserDetailsModel(
    nickname = this.login.orEmpty(),
    name = this.name.orEmpty(),
    company = this.company.orEmpty(),
    avatarUrl = this.avatarUrl.orEmpty(),
    location = this.location.orEmpty(),
    followers = this.followers ?: 0,
    following = this.following ?: 0,
    repoUrl = this.reposUrl.orEmpty()
)