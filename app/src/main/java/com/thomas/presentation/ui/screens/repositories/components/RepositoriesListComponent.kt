package com.thomas.presentation.ui.screens.repositories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun RepositoriesListComponentPreview() {
    RepositoriesListComponent(
        listOf(
            RepositoryModel(
                id = 1,
                name = "Repository name 1",
                description = "Description 1",
                url = "https://url.com",
                starsAmount = 10,
                watchersAmount = 10,
                language = "Language 1",
                licenseName = "LicenseName 1"
            ),
            RepositoryModel(
                id = 2,
                name = "Repository name 2",
                description = "Description 2",
                url = "https://url.com",
                starsAmount = 20,
                watchersAmount = 20,
                language = "Language 2",
                licenseName = "LicenseName 2"
            ),
            RepositoryModel(
                id = 3,
                name = "Repository name 3",
                description = "Description 3",
                url = "https://url.com",
                starsAmount = 30,
                watchersAmount = 30,
                language = "Language 3",
                licenseName = "LicenseName 3"
            )
        )
    )
}