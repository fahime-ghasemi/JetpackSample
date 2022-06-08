package com.example.jetpackcompose.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.theme.Hint
import com.example.jetpackcompose.presentation.theme.searchBackground

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    CustomTextField(
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                null,
                modifier = Modifier.padding(start = 4.dp),
                tint = Hint
            )
        },
        trailingIcon = null,
        modifier = modifier
            .background(
                searchBackground,
                RoundedCornerShape(10.dp)
            )
            .padding(0.dp)
            .height(36.dp),
        fontSize = 17.sp,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus()
        }),
        placeholderText = stringResource(id = R.string.search)
    )
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize
) {

    val text = remember { mutableStateOf("") }
    BasicTextField(modifier = modifier
        .background(
            searchBackground,
            RoundedCornerShape(10.dp),
        )
        .fillMaxWidth(),
        value = text.value,
        onValueChange = {
            text.value = it
        },

        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = Hint,
            fontSize = fontSize
        )
        ,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (text.value.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = Hint,
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}
@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}