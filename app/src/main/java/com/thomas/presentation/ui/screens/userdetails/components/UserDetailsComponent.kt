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
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.thomas.domain.model.UserDetailsModel
import com.thomas.myapplication.R

@Composable
internal fun UserDetailsComponent(
    userDetails: UserDetailsModel,
    onRepositoryClick: () -> Unit
) {
    val shapeCard = RoundedCornerShape(0.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
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
            Row(modifier = Modifier.padding(16.dp)) {
                if (userDetails.avatarUrl.isNotBlank()) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(userDetails.avatarUrl)
                            .crossfade(true).build(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                        loading = {
                            CircularProgressIndicator(Modifier.size(40.dp))
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (userDetails.nickname.isNotBlank()) {
                        Text(
                            text = stringResource(R.string.nickname_at, userDetails.nickname),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    if (userDetails.name.isNotBlank()) {
                        Text(
                            text = stringResource(R.string.name_at, userDetails.name),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    if (userDetails.company.isNotBlank()) {
                        Text(
                            text = stringResource(R.string.company_at, userDetails.company),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            if (userDetails.location.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = userDetails.location,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.followers_at, userDetails.followers),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(R.string.following_at, userDetails.following),
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