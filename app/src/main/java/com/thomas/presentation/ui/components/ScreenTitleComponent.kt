package com.thomas.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.thomas.myapplication.R
import com.thomas.presentation.ui.theme.dimen

@Composable
internal fun ScreenTitleComponent(@StringRes text: Int, onBackClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimen.small)
            .padding(top = MaterialTheme.dimen.normal)
    ) {
        onBackClick?.let {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                onClick = onBackClick
            ) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimen.iconMedium),
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = stringResource(R.string.back_icon_content_description),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(MaterialTheme.dimen.normal),
            text = stringResource(text),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}