package com.thomas.presentation.ui.screens.userdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.thomas.domain.model.UserDetailsModel
import com.thomas.myapplication.R
import com.thomas.presentation.ui.theme.dimen

@Composable
internal fun UserDetailsComponent(
    userDetails: UserDetailsModel,
    onRepositoryClick: () -> Unit
) {
    val shapeCard = RoundedCornerShape(MaterialTheme.dimen.default)
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = MaterialTheme.dimen.elevationSmall,
                    shape = shapeCard,
                    spotColor = MaterialTheme.colorScheme.secondary
                )
                .clip(shapeCard),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            shape = shapeCard,
        ) {
            Column {
                Row(modifier = Modifier.padding(MaterialTheme.dimen.normal)) {
                    if (userDetails.avatarUrl.isNotBlank()) {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .size(MaterialTheme.dimen.iconExtraLarge)
                                .clip(CircleShape),
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(userDetails.avatarUrl)
                                .crossfade(true).build(),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                            loading = {
                                CircularProgressIndicator(Modifier.size(MaterialTheme.dimen.iconLarge))
                            }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .height(MaterialTheme.dimen.iconExtraLarge)
                            .padding(start = MaterialTheme.dimen.normal),
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (userDetails.nickname.isNotBlank()) {
                            Text(
                                text = stringResource(R.string.nickname_at, userDetails.nickname),
                                maxLines = 1,
                                style = MaterialTheme.typography.titleSmall,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (userDetails.name.isNotBlank()) {
                            Text(
                                text = stringResource(R.string.name_at, userDetails.name),
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (userDetails.company.isNotBlank()) {
                            Text(
                                text = stringResource(R.string.company_at, userDetails.company),
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                if (userDetails.location.isNotBlank()) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.dimen.normal),
                        text = userDetails.location,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MaterialTheme.dimen.normal,
                            end = MaterialTheme.dimen.normal,
                            bottom = MaterialTheme.dimen.normal
                        ),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.normal)
                ) {
                    Text(
                        text = stringResource(R.string.followers_at, userDetails.followers),
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(R.string.following_at, userDetails.following),
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        if (userDetails.reposUrl.isNotBlank()) {
            RepositoriesItemComponent(onRepositoryClick = onRepositoryClick)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun UserDetailsComponentPreview() {
    UserDetailsComponent(
        userDetails = UserDetailsModel(
            nickname = "Nickname",
            name = "Name",
            company = "Company",
            avatarUrl = "https://avatar.url",
            location = "Location",
            followers = 10,
            following = 10,
            reposUrl = "https://avatar.url"
        ),
        onRepositoryClick = {}
    )
}