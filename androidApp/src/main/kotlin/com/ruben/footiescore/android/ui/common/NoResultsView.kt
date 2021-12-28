package com.ruben.footiescore.android.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 11/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.NoResultsView(
    modifier: Modifier = Modifier,
    enterTransition: EnterTransition,
    exitTransition: ExitTransition
) {
    Box(
        modifier = modifier.animateEnterExit(
            enter = enterTransition,
            exit = exitTransition
        )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.all_no_results_found),
            style = FootieScoreTheme.typography.subtitle1,
            color = FootieScoreTheme.colors.surface
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewNoResultsView() {
    AnimatedVisibility(visible = true) {
        NoResultsView(enterTransition = fadeInAnim(), exitTransition = fadeOutAnim())
    }
}