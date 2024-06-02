package com.thomas.presentation.ui.base

internal interface UiState {
    fun showError(message: String): UiState
    fun hideError(): UiState
    fun startScreenLoading(): UiState
    fun stopScreenLoading(): UiState
}