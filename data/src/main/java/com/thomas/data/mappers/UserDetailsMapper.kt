package com.thomas.data.mappers

import com.thomas.data.model.UserDetailResponse
import com.thomas.domain.model.UserDetailsModel

internal fun UserDetailResponse.toDomain() = UserDetailsModel(
    nickname = this.login.orEmpty(),
    name = this.name.orEmpty(),
    email = this.email.orEmpty(),
    avatarUrl = this.avatarUrl.orEmpty(),
    repoUrl = this.reposUrl.orEmpty()
)