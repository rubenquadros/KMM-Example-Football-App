package com.ruben.footiescore.android.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 30/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.ErrorView(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onClick: (() -> Unit)? = null,
    enterTransition: EnterTransition,
    exitTransition: ExitTransition
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateEnterExit(
                enter = enterTransition,
                exit = exitTransition
            )
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(id = R.string.content_description_error)
        )

        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = errorMessage,
            style = FootieScoreTheme.typography.body1,
            color = FootieScoreTheme.colors.surface,
            textAlign = TextAlign.Center
        )

        if (onClick != null) {
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                shape = FootieScoreTheme.shapes.largeRoundCornerShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = FootieScoreTheme.colors.error,
                    contentColor = FootieScoreTheme.colors.onPrimary
                ),
                onClick = onClick
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.all_retry),
                    style = FootieScoreTheme.typography.button
                )
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewErrorView(@PreviewParameter(ClickProvider::class) onClick: (() -> Unit)?) {
    AnimatedVisibility(modifier = Modifier.background(FootieScoreTheme.colors.onPrimary), visible = true) {
        ErrorView(
            errorMessage = stringResource(id = R.string.all_generic_error),
            enterTransition = fadeInAnim(),
            exitTransition = fadeOutAnim(),
            onClick = onClick
        )
    }
}

class ClickProvider : PreviewParameterProvider<(() -> Unit)?> {
    override val values: Sequence<(() -> Unit)?> =
        sequenceOf(
            null,
            { /* do something */ }
        )
}