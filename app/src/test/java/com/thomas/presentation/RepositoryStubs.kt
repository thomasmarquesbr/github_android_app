package com.thomas.presentation

import com.thomas.domain.model.RepositoryModel

object RepositoryStubs {
    val listRepositories = listOf(
        RepositoryModel(
            id = 1,
            name = "name1",
            description = "description1",
            url = "https://repository1.com",
            starsAmount = 10,
            watchersAmount = 100,
            language = "language1",
            licenseName = "licenseName1"
        ),
        RepositoryModel(
            id = 2,
            name = "name2",
            description = "description2",
            url = "https://repository2.com",
            starsAmount = 20,
            watchersAmount = 200,
            language = "language2",
            licenseName = "licenseName2"
        )
    )
}