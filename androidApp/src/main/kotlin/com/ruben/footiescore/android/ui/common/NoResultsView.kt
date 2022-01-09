package com.ruben.footiescore.android.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 11/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.NoResultsView(
    modifier: Modifier = Modifier,
    enterTransition: EnterTransition = fadeInAnim(),
    exitTransition: ExitTransition = fadeOutAnim(),
    message: String
) {
    Box(
        modifier = modifier.animateEnterExit(
            enter = enterTransition,
            exit = exitTransition
        )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            style = FootieScoreTheme.typography.subtitle1,
            color = FootieScoreTheme.colors.surface,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.NoLiveMatchesView(
    modifier: Modifier = Modifier,
    enterTransition: EnterTransition,
    exitTransition: ExitTransition,
    message: String
) {
    Column(
        modifier = modifier.animateEnterExit(
            enter = enterTransition,
            exit = exitTransition
        )
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_live_off),
            contentDescription = stringResource(id = R.string.content_description_not_live)
        )

        NoResultsView(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            message = message
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewNoResultsView() {
    AnimatedVisibility(visible = true) {
        NoResultsView(
            enterTransition = fadeInAnim(),
            exitTransition = fadeOutAnim(),
            message = stringResource(id = R.string.all_no_results_found)
        )
    }
}

@Preview
@Composable
fun PreviewNoLiveMatchesView() {
    AnimatedVisibility(visible = true) {
        NoLiveMatchesView(
            enterTransition = fadeInAnim(),
            exitTransition = fadeOutAnim(),
            message = stringResource(id = R.string.live_matches_not_live)
        )
    }
}