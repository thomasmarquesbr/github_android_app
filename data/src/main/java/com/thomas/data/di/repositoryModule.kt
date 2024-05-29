package com.thomas.data.di

import com.thomas.data.repository.GithubRepositoryImpl
import com.thomas.data.repository.GithubAPIDataSource
import com.thomas.domain.GithubRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<GithubRepository> {
        GithubRepositoryImpl(
            get() as GithubAPIDataSource
        )
    }
}