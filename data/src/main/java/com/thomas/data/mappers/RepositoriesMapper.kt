package com.thomas.data.mappers

import com.thomas.data.model.RepositoryResponse
import com.thomas.domain.model.RepositoryModel

internal fun RepositoryResponse.toDomain() = RepositoryModel(
    id = this.id ?: -1,
    name = this.name.orEmpty(),
    description = this.description.orEmpty(),
    url = this.htmlUrl.orEmpty(),
    starsAmount = this.stargazersCount ?: 0,
    watchersAmount = this.watchersCount ?: 0,
    language = this.language.orEmpty(),
    licenseName = this.license?.name.orEmpty()
)

internal fun List<RepositoryResponse>.toDomain(): List<RepositoryModel> =
    this.map { it.toDomain() }