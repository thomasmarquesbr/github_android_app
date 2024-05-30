package com.thomas.presentation.ui.screens.users

import com.thomas.domain.model.UserModel
import com.thomas.presentation.ui.base.UiState

internal data class UsersUiState(
    val content: List<UserModel> = listOf(),
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

    fun updateContent(content: List<UserModel>) = copy(
        content = content
    )
}
