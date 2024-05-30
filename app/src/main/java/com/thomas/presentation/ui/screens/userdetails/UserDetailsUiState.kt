package com.thomas.presentation.ui.screens.userdetails

import com.thomas.presentation.ui.base.UiState

internal data class UserDetailsUiState(
    val content: String = "",
    val loading: Boolean = true,
    val errorMessage: String = ""
): UiState {
    override fun showError(message: String) = copy(
        errorMessage = message
    )

    override fun hideError() = copy(
        errorMessage = ""
    )

    override fun startScreenLoading() = copy(
        loading = true
    )

    override fun stopScreenLoading() = copy(
        loading = false
    )

    fun updateContent(userDetails: String) = copy(
        content = userDetails
    )
}