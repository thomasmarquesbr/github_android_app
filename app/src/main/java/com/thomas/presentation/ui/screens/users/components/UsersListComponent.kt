package com.thomas.presentation.ui.screens.users.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thomas.domain.model.UserModel
import com.thomas.myapplication.R
import com.thomas.presentation.ui.components.SearchTextField

@Composable
internal fun UsersListComponent(
    searchText: String,
    users: List<UserModel>,
    onSearchValue: (String) -> Unit,
    onItemClick: (UserModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            SearchTextField(
                text = searchText,
                onValueChanged = onSearchValue,
                placeHolder = stringResource(R.string.search_user_placeholder)
            )
        }
        items(users) { user ->
            UserItemComponent(
                user = user,
                onItemClick = { onItemClick.invoke(user) }
            )
        }
    }
}