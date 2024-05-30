package com.thomas.presentation.ui.screens.users

import androidx.lifecycle.viewModelScope
import com.thomas.domain.model.UserModel
import com.thomas.domain.usecases.GetUsersUseCase
import com.thomas.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val goToUserDetails: (String) -> Unit,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseViewModel<UsersUiState>(UsersUiState()) {

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.invoke()
                .flowOn(dispatcher)
                .onStart { setState { it.startScreenLoading() } }
                .onCompletion { setState { it.stopScreenLoading() } }
                .collect { users ->
                    setState { it.updateContent(users) }
                }
        }
    }

    fun onUserItemClicked(user: UserModel) {
        goToUserDetails.invoke(user.nickname)
    }
}