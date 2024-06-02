package com.thomas.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thomas.myapplication.R
import com.thomas.presentation.ui.theme.dimen

private val AppTextInputColors: TextFieldColors
    @Composable
    get() = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
        unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary,
        cursorColor = MaterialTheme.colorScheme.primary,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
        errorBorderColor = MaterialTheme.colorScheme.onBackground,
        errorTextColor = MaterialTheme.colorScheme.onBackground,
        errorLeadingIconColor = MaterialTheme.colorScheme.onBackground,
        errorTrailingIconColor = MaterialTheme.colorScheme.onBackground,
        errorLabelColor = MaterialTheme.colorScheme.onBackground,
        errorSupportingTextColor = MaterialTheme.colorScheme.error,
        focusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.onBackground
    )

@Composable
fun SearchTextField(
    text: String = "",
    onValueChanged: (String) -> Unit,
    placeHolder: String = "",
    onGoFocus: () -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(FocusRequester())
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = text,
        onValueChange = onValueChanged,
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_magnify),
                contentDescription = "",
                tint = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
        },
        shape = RoundedCornerShape(MaterialTheme.dimen.small),
        colors = AppTextInputColors,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go
        ),
        placeholder = { Text(text = placeHolder, style = MaterialTheme.typography.labelMedium) },
        keyboardActions = KeyboardActions(
            onGo = { onGoFocus.invoke() }
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun SearchTextFieldPreview() {
    SearchTextField(
        onValueChanged = {},
        text = "",
        placeHolder = "Campo de busca"
    )
}