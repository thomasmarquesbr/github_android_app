package com.thomas.presentation.ui.screens.repositories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.thomas.domain.model.RepositoryModel
import com.thomas.myapplication.R
import com.thomas.presentation.ui.theme.dimen
import com.thomas.presentation.ui.theme.yellow700

@Composable
internal fun RepositoryItemComponent(repository: RepositoryModel) {
    val uriHandler = LocalUriHandler.current
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = repository.name,
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.ic_star_outline),
                    contentDescription = stringResource(R.string.stars_content_description),
                    tint = yellow700
                )
                Spacer(modifier = Modifier.size(MaterialTheme.dimen.extraSmall))
                Text(
                    text = repository.starsAmount.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.size(MaterialTheme.dimen.medium))
                Icon(
                    painter = painterResource(R.drawable.ic_eye_outline),
                    contentDescription = stringResource(R.string.watchers_content_description),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.size(MaterialTheme.dimen.extraSmall))
                Text(
                    text = repository.watchersAmount.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        if (repository.description.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = repository.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (repository.language.isNotBlank()) {
            Text(
                text = stringResource(R.string.language_at, repository.language),
                style = MaterialTheme.typography.labelSmall
            )
        }
        if (repository.licenseName.isNotBlank()) {
            Text(
                text = stringResource(R.string.license_at, repository.licenseName),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = {
                uriHandler.openUri(repository.url)
            }) {
                Text(
                    text = stringResource(R.string.go_site),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
        Spacer(modifier = Modifier.size(MaterialTheme.dimen.extraSmall))
        HorizontalDivider()
        Spacer(modifier = Modifier.size(MaterialTheme.dimen.extraSmall))
    }
}