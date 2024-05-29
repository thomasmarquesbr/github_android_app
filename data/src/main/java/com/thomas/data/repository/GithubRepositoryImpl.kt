package com.thomas.data.repository

import com.thomas.domain.GithubRepository

internal class GithubRepositoryImpl(
    private val apiDataSource: GithubAPIDataSource
): GithubRepository {
}