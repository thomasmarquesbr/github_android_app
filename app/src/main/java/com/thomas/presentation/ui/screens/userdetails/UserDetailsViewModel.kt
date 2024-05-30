package com.thomas.presentation.ui.screens.userdetails

import androidx.lifecycle.viewModelScope
import com.thomas.domain.usecases.GetUserDetailsUseCase
import com.thomas.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class UserDetailsViewModel(
    private val username: String,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val goToRepositories: (String) -> Unit,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseViewModel<UserDetailsUiState>(UserDetailsUiState()) {
    init {
        getUserDetails()
    }

    fun getUserDetails() {
        viewModelScope.launch {
            getUserDetailsUseCase.invoke(username)
                .flowOn(dispatcher)
                .onStart { setState { it.startScreenLoading() } }
                .onCompletion { setState { it.stopScreenLoading() } }
                .catch { error ->
                    setState { it.showError(it.errorMessage) }
                }
                .collect { userDetails ->
                    setState { it.updateContent(userDetails) }
                }
        }
    }

    fun onRepositoryClick(repositoryUrl: String) {
        goToRepositories.invoke(repositoryUrl)
    }
}