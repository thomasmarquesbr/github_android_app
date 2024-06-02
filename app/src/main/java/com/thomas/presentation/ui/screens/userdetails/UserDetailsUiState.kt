package com.thomas.presentation.ui.screens.userdetails

import com.thomas.domain.model.UserDetailsModel
import com.thomas.presentation.ui.base.UiState

internal data class UserDetailsUiState(
    val content: UserDetailsModel = UserDetailsModel.initial,
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
        loading = true,
        errorMessage = ""
    )

    override fun stopScreenLoading() = copy(
        loading = false
    )

    fun updateContent(userDetails: UserDetailsModel) = copy(
        content = userDetails
    )
}