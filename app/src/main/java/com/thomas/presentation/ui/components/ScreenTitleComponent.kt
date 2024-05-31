package com.thomas.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thomas.myapplication.R
import com.thomas.presentation.ui.theme.baseGray400

@Composable
internal fun ScreenTitleComponent(@StringRes text: Int, onBackClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp)
    ) {
        onBackClick?.let {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                onClick = onBackClick
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = stringResource(R.string.back_icon_content_description),
                    tint = baseGray400
                )
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            text = stringResource(text),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = baseGray400
        )
    }
}