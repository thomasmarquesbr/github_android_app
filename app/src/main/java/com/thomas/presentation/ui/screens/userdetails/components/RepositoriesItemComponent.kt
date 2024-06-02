package com.thomas.presentation.ui.screens.userdetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.thomas.myapplication.R
import com.thomas.presentation.ui.theme.dimen

@Composable
internal fun RepositoriesItemComponent(onRepositoryClick: () -> Unit) {
    val shapeCard = RoundedCornerShape(MaterialTheme.dimen.default)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.dimen.medium)
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
                onClick = {
                    onRepositoryClick.invoke()
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        shape = shapeCard,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimen.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.repositories),
                style = MaterialTheme.typography.labelMedium
            )
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = stringResource(R.string.go_repositories_content_description),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}