package com.thomas.domain.di

import com.thomas.domain.usecases.GetRepositoriesUseCase
import com.thomas.domain.usecases.GetUserDetailsUseCase
import com.thomas.domain.usecases.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetUsersUseCase(get())
    }

    factory {
        GetUserDetailsUseCase(get())
    }

    factory {
        GetRepositoriesUseCase(get())
    }
}
