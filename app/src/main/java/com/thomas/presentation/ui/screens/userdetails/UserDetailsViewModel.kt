package com.thomas.presentation.ui.screens.userdetails

import com.thomas.presentation.ui.base.BaseViewModel

internal class UserDetailsViewModel(
    private val username: String
): BaseViewModel<UserDetailsUiState>(UserDetailsUiState()) {

}