package com.thomas.data.mappers

import com.thomas.data.model.UserResponse
import com.thomas.domain.model.UserModel

internal fun UserResponse.toDomain(): UserModel = UserModel(
    nickname = this.login.orEmpty(),
    avatarUrl = this.avatarUrl.orEmpty()
)

internal fun List<UserResponse>.toDomain(): List<UserModel> =
    this.map { it.toDomain() }