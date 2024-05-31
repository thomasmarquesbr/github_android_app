package com.thomas.presentation.ui.screens.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.thomas.domain.model.UserModel
import com.thomas.myapplication.R
import com.thomas.presentation.ui.components.LoadingStateComponent
import com.thomas.presentation.ui.components.MessageStateComponent
import com.thomas.presentation.ui.components.ScreenTitleComponent
import com.thomas.presentation.ui.theme.baseGray400
import java.util.EmptyStackException

@Composable
internal fun UsersScreen(
    viewModel: UsersViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitleComponent(text = R.string.users_screen_title)
        when {
            uiState.loading -> {
                LoadingStateComponent()
            }

            uiState.errorMessage.isNotBlank() -> {
                MessageStateComponent(
                    message = uiState.errorMessage,
                    onButtonClick = viewModel::getUsers
                )
            }

            uiState.content.isEmpty() -> {
                MessageStateComponent(
                    message = stringResource(R.string.empty_users_state),
                    onButtonClick = viewModel::getUsers
                )
            }

            else -> {
                UsersListComponent(
                    users = uiState.content,
                    onItemClick = viewModel::onUserItemClicked
                )
            }
        }
    }
}

@Composable
internal fun UsersListComponent(users: List<UserModel>, onItemClick: (UserModel) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users) { user ->
            UserItemComponent(
                user = user,
                onItemClick = { onItemClick.invoke(user) }
            )
        }
    }
}

@Composable
internal fun UserItemComponent(user: UserModel, onItemClick: () -> Unit) {
    val shapeCard = RoundedCornerShape(6.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = shapeCard, spotColor = baseGray400)
            .clip(shapeCard)
            .clickable(
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = onItemClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = shapeCard,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (user.avatarUrl.isNotBlank()) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(user.avatarUrl)
                        .crossfade(true).build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator(Modifier.size(20.dp))
                    }
                )
            }
            Text(text = user.nickname, fontWeight = FontWeight.Medium, fontSize = 18.sp)
        }
    }
}
