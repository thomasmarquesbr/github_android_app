package com.thomas.presentation.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal abstract class BaseViewModel<State : UiState>(
    initialState: State
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _state

    protected fun setState(state: (State) -> State) {
        _state.update { state(_state.value) }
    }
}