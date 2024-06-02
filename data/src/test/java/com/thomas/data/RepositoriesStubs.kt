package com.thomas.data

import com.thomas.data.model.RepositoryResponse
import com.thomas.domain.model.RepositoryModel

internal object RepositoriesStubs {
    val listModel = listOf(
        RepositoryModel(
            id = 79171906,
            name = "libdc-for-dirk",
            description = "Only use for syncing with Dirk, don't use for anything else",
            url = "https://github.com/torvalds",
            starsAmount = 219,
            watchersAmount = 219,
            language = "C",
            licenseName = "GNU Lesser General Public License v2.1"
        ),
        RepositoryModel(
            id = 519408694,
            name = "R_kgDOHvWMNg",
            description = "A cross-platform, linkable library implementation of Git that you can use in your application.",
            url = "https://github.com/torvalds/libgit2",
            starsAmount = 123,
            watchersAmount = 123,
            language = "C",
            licenseName = "Other"
        )
    )

    val listResponse = listOf(
        RepositoryResponse(
            id = 79171906,
            name = "libdc-for-dirk",
            description = "Only use for syncing with Dirk, don't use for anything else",
            htmlUrl = "https://github.com/torvalds",
            stargazersCount = 219,
            watchersCount = 219,
            language = "C",
            license = RepositoryResponse.License(name = "GNU Lesser General Public License v2.1")
        ),
        RepositoryResponse(
            id = 519408694,
            name = "R_kgDOHvWMNg",
            description = "A cross-platform, linkable library implementation of Git that you can use in your application.",
            htmlUrl = "https://github.com/torvalds/libgit2",
            stargazersCount = 123,
            watchersCount = 123,
            language = "C",
            license = RepositoryResponse.License(name = "Other")
        )
    )
}