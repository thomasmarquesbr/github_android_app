package com.thomas.presentation.ui.screens.repositories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thomas.domain.model.RepositoryModel
import com.thomas.presentation.ui.theme.dimen

@Composable
internal fun RepositoriesListComponent(repositories: List<RepositoryModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = MaterialTheme.dimen.small,
            start = MaterialTheme.dimen.normal,
            end = MaterialTheme.dimen.normal,
            bottom = MaterialTheme.dimen.normal
        ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.medium)
    ) {
        items(repositories) { repository ->
            RepositoryItemComponent(repository)
        }
    }
}