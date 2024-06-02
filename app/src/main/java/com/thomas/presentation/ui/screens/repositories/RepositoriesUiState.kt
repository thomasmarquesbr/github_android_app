package com.thomas.presentation.ui.screens.repositories

import com.thomas.domain.model.RepositoryModel
import com.thomas.presentation.ui.base.UiState

internal data class RepositoriesUiState(
    val content: List<RepositoryModel> = listOf(),
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

    fun updateContent(repositories: List<RepositoryModel>) = copy(
        content = repositories
    )
}
