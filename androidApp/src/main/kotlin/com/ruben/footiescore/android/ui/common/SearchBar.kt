package com.ruben.footiescore.android.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.utility.shouldPerformSearch

/**
 * Created by Ruben Quadros on 27/11/21
 **/
@Composable
fun TopSearchBar(
    searchState: TextFieldValue,
    onValueChanged: (value: TextFieldValue) -> Unit,
    onClear: () -> Unit,
    onSearch: (String) -> Unit,
    backHandler: (() -> Unit)? = null
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

//    DisposableEffect(Unit) {
//        onDispose { focusRequester.freeFocus() }
//    }

    Card(
        elevation = 10.dp
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .testTag("Top Search Bar"),
            value = searchState,
            onValueChange = {
                if (searchState.text.trim() != it.text.trim() && it.text.trim()
                        .shouldPerformSearch()
                ) {
                    onSearch.invoke(it.text)
                }
                onValueChanged.invoke(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.background(Color.Transparent),
                    text = stringResource(id = R.string.select_team_hint),
                    color = FootieScoreTheme.colors.disabled,
                    style = FootieScoreTheme.typography.body1
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = FootieScoreTheme.colors.onPrimary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = FootieScoreTheme.colors.secondary,
                textColor = FootieScoreTheme.colors.surface
            ),
            textStyle = FootieScoreTheme.typography.body1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch.invoke(searchState.text)
                    focusRequester.freeFocus()
                }
            ),
            leadingIcon = {
                IconButton(
                    enabled = backHandler != null,
                    onClick = {
                        focusRequester.freeFocus()
                        backHandler?.invoke()
                    }) {
                    Image(
                        painter = if (backHandler == null) painterResource(id = R.drawable.ic_search)
                        else painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(id = R.string.content_description_back_button)
                    )
                }
            },
            trailingIcon = {
                if (searchState.text.isNotEmpty()) {
                    IconButton(onClick = {
                        onClear.invoke()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = stringResource(id = R.string.content_description_clear_button)
                        )
                    }
                }
            }
        )
    }
}

@Preview(name = "Top search bar")
@Composable
fun PreviewTopSearchBarInitState(@PreviewParameter(SearchStateProvider::class) searchState: TextFieldValue) {
    TopSearchBar(
        searchState = searchState,
        onSearch = {},
        onClear = {},
        onValueChanged = {}
    )
}

class SearchStateProvider : PreviewParameterProvider<TextFieldValue> {
    override val values: Sequence<TextFieldValue> =
        sequenceOf(
            TextFieldValue(),
            TextFieldValue("Manchester")
        )
}