package com.thomas.presentation.ui.screens.repositories

import androidx.lifecycle.viewModelScope
import com.thomas.domain.usecases.GetRepositoriesUseCase
import com.thomas.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class RepositoriesViewModel(
    private val username: String,
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val goBackClick: () -> Unit,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseViewModel<RepositoriesUiState>(RepositoriesUiState()) {
    init {
        getRepositories()
    }

    fun getRepositories() {
        viewModelScope.launch {
            getRepositoriesUseCase.invoke(username)
                .flowOn(dispatcher)
                .onStart { setState { it.startScreenLoading() } }
                .onCompletion { setState { it.stopScreenLoading() } }
                .catch { error ->
                    setState { it.showError(error.message.orEmpty()) }
                }
                .collect { repositories ->
                    setState { it.updateContent(repositories) }
                }
        }
    }

    fun onBackClick() {
        goBackClick.invoke()
    }
}