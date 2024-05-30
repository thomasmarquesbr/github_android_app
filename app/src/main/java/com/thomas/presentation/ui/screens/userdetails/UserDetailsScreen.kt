package com.thomas.presentation.ui.screens.userdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        ScreenTitleComponent(text = R.string.user_details_screen_title)
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
    onRepositoryClick: (String) -> Unit
) {
    val shapeCard = RoundedCornerShape(6.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(elevation = 2.dp, shape = shapeCard, spotColor = baseGray400)
            .clip(shapeCard),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = shapeCard,
    ) {
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
            Column(modifier = Modifier.padding(start = 16.dp)) {
                if (userDetails.nickname.isNotBlank()) {
                    Text(text = stringResource(R.string.nickname_at, userDetails.nickname))
                }
                if (userDetails.name.isNotBlank()) {
                    Text(text = stringResource(R.string.name_at, userDetails.name))
                }
                if (userDetails.email.isNotBlank()) {
                    Text(text = stringResource(R.string.email_at, userDetails.email))
                }
            }
        }
    }
    if (userDetails.repoUrl.isNotBlank()) {
        RepositoriesItemComponent(userDetails.repoUrl, onRepositoryClick = onRepositoryClick)
    }
}

@Composable
private fun RepositoriesItemComponent(repoUrl: String, onRepositoryClick: (String) -> Unit) {
    val shapeCard = RoundedCornerShape(6.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .shadow(elevation = 2.dp, shape = shapeCard, spotColor = baseGray400)
            .clip(shapeCard)
            .clickable(
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = {
                    onRepositoryClick.invoke(repoUrl)
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


