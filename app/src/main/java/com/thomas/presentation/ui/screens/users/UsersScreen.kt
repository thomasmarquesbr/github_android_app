package com.thomas.presentation.ui.screens.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thomas.myapplication.R
import com.thomas.presentation.ui.components.LoadingStateComponent
import com.thomas.presentation.ui.components.MessageStateComponent
import com.thomas.presentation.ui.components.ScreenTitleComponent
import com.thomas.presentation.ui.screens.users.components.UsersListComponent

@Composable
internal fun UsersScreen(
    viewModel: UsersViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitleComponent(text = R.string.users_screen_title)
        when {
            uiState.loading -> {
                LoadingStateComponent()
            }

            uiState.errorMessage.isNotBlank() -> {
                MessageStateComponent(
                    message = uiState.errorMessage,
                    onButtonClick = viewModel::getUsers
                )
            }

            uiState.filteredContent.isEmpty() && uiState.searchText.isEmpty() -> {
                MessageStateComponent(
                    message = stringResource(R.string.empty_users_state),
                    onButtonClick = viewModel::getUsers
                )
            }

            else -> {
                UsersListComponent(
                    users = uiState.filteredContent,
                    searchText = uiState.searchText,
                    onSearchValue = viewModel::onSearchValue,
                    onItemClick = viewModel::onUserItemClicked
                )
            }
        }
    }
}
