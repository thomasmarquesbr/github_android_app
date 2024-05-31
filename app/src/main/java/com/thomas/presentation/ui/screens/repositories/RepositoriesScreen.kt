package com.thomas.presentation.ui.screens.repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thomas.domain.model.RepositoryModel
import com.thomas.myapplication.R
import com.thomas.presentation.ui.components.LoadingStateComponent
import com.thomas.presentation.ui.components.MessageStateComponent
import com.thomas.presentation.ui.components.ScreenTitleComponent
import com.thomas.presentation.ui.theme.baseGray400
import com.thomas.presentation.ui.theme.yellow700

@Composable
internal fun RepositoriesScreen(
    viewModel: RepositoriesViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitleComponent(
            text = R.string.repositories_screen_title,
            onBackClick = viewModel::onBackClick
        )
        when {
            uiState.loading -> {
                LoadingStateComponent()
            }

            uiState.errorMessage.isNotBlank() -> {
                MessageStateComponent(
                    message = uiState.errorMessage,
                    onButtonClick = viewModel::getRepositories
                )
            }

            uiState.content.isEmpty() -> {
                MessageStateComponent(
                    message = stringResource(R.string.empty_repositories_state),
                    onButtonClick = viewModel::getRepositories
                )
            }

            else -> {
                RepositoriesListComponent(repositories = uiState.content)
            }
        }
    }
}

@Composable
private fun RepositoriesListComponent(repositories: List<RepositoryModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(repositories) { repository ->
            RepositoryItemComponent(repository)
        }
    }
}

@Composable
private fun RepositoryItemComponent(repository: RepositoryModel) {
    val uriHandler = LocalUriHandler.current
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = repository.name, fontSize = 18.sp, overflow = TextOverflow.Ellipsis)
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_star_outline),
                    contentDescription = stringResource(R.string.stars_content_description),
                    tint = yellow700
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = repository.starsAmount.toString(),
                    fontSize = 14.sp,
                    color = baseGray400
                )
                Spacer(modifier = Modifier.size(12.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_eye_outline),
                    contentDescription = stringResource(R.string.watchers_content_description),
                    tint = baseGray400
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = repository.watchersAmount.toString(),
                    fontSize = 14.sp,
                    color = baseGray400
                )
            }
        }
        if (repository.description.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = repository.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (repository.language.isNotBlank()) {
            Text(
                text = stringResource(R.string.language_at, repository.language),
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraLight
            )
        }
        if (repository.licenseName.isNotBlank()) {
            Text(
                text = stringResource(R.string.license_at, repository.licenseName),
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraLight
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = {
                uriHandler.openUri(repository.url)
            }) {
                Text(text = stringResource(R.string.go_site))
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.size(4.dp))
    }
}