package com.thomas.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thomas.myapplication.R
import com.thomas.presentation.ui.theme.dimen

@Composable
internal fun MessageStateComponent(message: String, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.dimen.normal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.size(MaterialTheme.dimen.small))
        Button(onClick = onButtonClick) {
            Text(text = stringResource(R.string.retry), style = MaterialTheme.typography.bodyLarge)
        }
    }
}