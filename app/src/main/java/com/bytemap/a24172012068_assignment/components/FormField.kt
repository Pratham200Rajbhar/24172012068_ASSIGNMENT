package com.bytemap.a24172012068_assignment.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun FormField(
    label: String,
    textState: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector,
    isPasswordField: Boolean = false,
    isNumber: Boolean = false
) {
    OutlinedTextField(
        value = textState,
        onValueChange = onTextChange,
        label = { Text(text = "Enter $label") },
        trailingIcon = { Icon(imageVector = icon, contentDescription = "$label icon") }, // dynamic icon
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default
    )
}


@Composable
@Preview(showBackground = true)
fun FormFieldPreview(){
    FormField(
        label = "Email",
        textState = "",
        onTextChange = {},
        icon = Icons.Default.Person
    )
}