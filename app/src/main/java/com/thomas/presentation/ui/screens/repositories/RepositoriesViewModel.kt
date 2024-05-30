package com.thomas.presentation.ui.screens.repositories

import com.thomas.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class RepositoriesViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseViewModel<RepositoriesUiState>(RepositoriesUiState()) {
}