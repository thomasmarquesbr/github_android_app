package com.thomas.presentation.ui.screens.userdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.thomas.domain.model.UserDetailsModel
import com.thomas.myapplication.R
import com.thomas.presentation.ui.components.LoadingStateComponent
import com.thomas.presentation.ui.components.MessageStateComponent
import com.thomas.presentation.ui.components.ScreenTitleComponent
import com.thomas.presentation.ui.theme.baseGray400

@Composable
internal fun UserDetailsScreen(
    viewModel: UserDetailsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitleComponent(
            text = R.string.user_details_screen_title,
            onBackClick = viewModel::onBackClick
        )
        when {
            uiState.loading -> {
                LoadingStateComponent()
            }

            uiState.errorMessage.isNotBlank() -> {
                MessageStateComponent(
                    message = uiState.errorMessage,
                    onButtonClick = viewModel::getUserDetails
                )
            }

            else -> {
                UserDetailsComponent(
                    userDetails = uiState.content,
                    onRepositoryClick = viewModel::onRepositoryClick
                )
            }
        }
    }
}

@Composable
private fun UserDetailsComponent(
    userDetails: UserDetailsModel,
    onRepositoryClick: () -> Unit
) {
    val shapeCard = RoundedCornerShape(0.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = shapeCard, spotColor = baseGray400)
            .clip(shapeCard),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
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
    if (userDetails.repoUrl.isNotBlank()) {
        RepositoriesItemComponent(onRepositoryClick = onRepositoryClick)
    }
}

@Composable
private fun RepositoriesItemComponent(onRepositoryClick: () -> Unit) {
    val shapeCard = RoundedCornerShape(0.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .shadow(elevation = 2.dp, shape = shapeCard, spotColor = baseGray400)
            .clip(shapeCard)
            .clickable(
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = {
                    onRepositoryClick.invoke()
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = shapeCard,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.repositories), fontSize = 16.sp)
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = stringResource(R.string.go_repositories_content_description),
                tint = baseGray400
            )
        }
    }
}


