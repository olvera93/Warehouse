package com.olvera.thewarehouse.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WareTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    leadingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    isDate: Boolean = false,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    var hidePassword by remember {
        mutableStateOf(true)
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { this.contentDescription = contentDescription },
            leadingIcon = if (leadingIcon == null) null else {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null
                    )
                }
            },
            enabled = isEnabled,
            isError = errorMessage != null,
            trailingIcon = {
                if (isPassword) {
                    TextButton(
                        onClick = { hidePassword = !hidePassword },
                        enabled = isEnabled
                    ) {
                        Text(
                            text = if (hidePassword) "Show" else "Hide",
                            color = MaterialTheme.colorScheme.tertiary,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            },
            placeholder = { Text(text = placeholder) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                ),
                focusedPlaceholderColor = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                ),
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent
            ),
            visualTransformation = if (isPassword && hidePassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun WarePasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    placeholder: String = "Password",
    errorMessage: String? = null,
    leadingIcon: ImageVector? = Icons.Outlined.Lock,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    WareTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = modifier,
        errorMessage = errorMessage,
        leadingIcon = leadingIcon,
        isPassword = true,
        isEnabled = isEnabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        backgroundColor = backgroundColor,
        contentDescription = contentDescription
    )
}

@Composable
fun WareDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    placeholder: String = "Date",
    errorMessage: String? = null,
    leadingIcon: ImageVector? = Icons.Outlined.Lock,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    WareTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = modifier,
        errorMessage = errorMessage,
        leadingIcon = leadingIcon,
        isDate = true,
        isEnabled = isEnabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        backgroundColor = backgroundColor,
        contentDescription = contentDescription
    )
}
@Preview
@Composable
fun WareTextFieldPreview() {
    WareTextField(
        value = "",
        onValueChange = {},
        leadingIcon = Icons.Outlined.Email,
        placeholder = "Email",
        contentDescription = ""
    )
}