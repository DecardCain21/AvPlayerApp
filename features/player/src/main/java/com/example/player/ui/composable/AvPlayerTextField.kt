package com.example.player.ui.composable

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public fun AvPlayerTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(30.dp),
    interactionSource: MutableInteractionSource? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    containerColor: Color = Color.LightGray
) {

    val textState = remember { mutableStateOf(TextFieldValue(value)) }

    LaunchedEffect(value) {
        textState.value = TextFieldValue(value, selection = TextRange(value.length))
    }

    TextField(
        modifier = modifier,
        value = textState.value,
        onValueChange = { newValue ->
            textState.value = newValue.copy(selection = newValue.selection)
            onValueChange(newValue.text)
        },
        interactionSource = interactionSource,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor
        ),
        leadingIcon = leadingIcon?.let { icon ->
            { icon() }
        },
        shape = roundedCornerShape,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    )
}