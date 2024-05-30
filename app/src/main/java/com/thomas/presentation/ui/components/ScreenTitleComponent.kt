package com.thomas.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thomas.myapplication.R

@Composable
internal fun ScreenTitleComponent(@StringRes text: Int) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(text),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
}