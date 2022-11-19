package com.example.jetpackcompose.presentation.component

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.theme.Hint
import com.example.jetpackcompose.presentation.theme.searchBackground

/**
 * I've used BasicTextField here because of searchbar height. I couldn't use TextField here
 * because it had a padding around and we can't remove it.
 * It causes we can't have a TextField with a small height like one we have here in the design
 */
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    Log.i("fahi","recompose searchbar")
    val focusManager = LocalFocusManager.current

    CustomTextField(
        value = value,
        onValueChange = onValueChange,
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
            .padding(0.dp),
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
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize
) {

    BasicTextField(modifier = modifier
        .background(
            searchBackground,
            RoundedCornerShape(10.dp),
        )
        .fillMaxWidth()
        .onFocusChanged {  },
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = Hint,
            fontSize = fontSize
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (value.text.isEmpty()) Text(
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
    SearchBar(Modifier.height(searchBarHeight), TextFieldValue("")) {}
}