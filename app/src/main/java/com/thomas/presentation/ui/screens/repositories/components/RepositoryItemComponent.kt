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
import com.thomas.presentation.ui.theme.yellow700

@Composable
internal fun RepositoryItemComponent(repository: RepositoryModel) {
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
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.size(12.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_eye_outline),
                    contentDescription = stringResource(R.string.watchers_content_description),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = repository.watchersAmount.toString(),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
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