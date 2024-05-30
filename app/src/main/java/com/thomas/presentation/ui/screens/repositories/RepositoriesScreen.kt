package com.thomas.presentation.ui.screens.repositories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
internal fun Repositories(
    viewModel: RepositoriesViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
}