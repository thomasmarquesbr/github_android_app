package com.thomas.presentation.ui.screens.users.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.thomas.domain.model.UserModel
import com.thomas.presentation.ui.theme.dimen

@Composable
internal fun UserItemComponent(user: UserModel, onItemClick: () -> Unit) {
    val shapeCard = RoundedCornerShape(MaterialTheme.dimen.small)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = MaterialTheme.dimen.elevationSmall,
                shape = shapeCard,
                spotColor = MaterialTheme.colorScheme.secondary
            )
            .clip(shapeCard)
            .clickable(
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = onItemClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        shape = shapeCard,
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.dimen.normal),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.normal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (user.avatarUrl.isNotBlank()) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(MaterialTheme.dimen.iconLarge)
                        .clip(CircleShape),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(user.avatarUrl)
                        .crossfade(true).build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator(Modifier.size(MaterialTheme.dimen.iconSmall))
                    }
                )
            }
            Text(text = user.nickname, style = MaterialTheme.typography.titleSmall)
        }
    }
}