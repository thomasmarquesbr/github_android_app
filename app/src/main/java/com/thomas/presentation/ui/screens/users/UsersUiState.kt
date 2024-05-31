package com.thomas.presentation.ui.screens.users

import com.thomas.domain.model.UserModel
import com.thomas.presentation.ui.base.UiState

internal data class UsersUiState(
    val content: List<UserModel> = listOf(),
    val filteredContent: List<UserModel> = listOf(),
    val searchText: String = "",
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

    fun updateContent(users: List<UserModel>) = copy(
        content = users,
        filteredContent = users
    )

    fun updateSearchValue(value: String) = copy(
        searchText = value,
        filteredContent = if (value.isNotBlank())
            content.filter { it.nickname.contains(value, true) } else content
    )
}
