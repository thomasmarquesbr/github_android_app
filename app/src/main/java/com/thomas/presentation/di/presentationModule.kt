package com.thomas.presentation.di

import com.thomas.presentation.ui.screens.userdetails.UserDetailsViewModel
import com.thomas.presentation.ui.screens.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {(goToUserDetails: (String) -> Unit) ->
        UsersViewModel(
            getUsersUseCase = get(),
            goToUserDetails = goToUserDetails
        )
    }

    viewModel { (username: String) ->
        UserDetailsViewModel(username = username)
    }
}
