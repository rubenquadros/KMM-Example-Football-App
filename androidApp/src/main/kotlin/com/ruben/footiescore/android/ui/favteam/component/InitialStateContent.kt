package com.ruben.footiescore.android.ui.favteam.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim

/**
 * Created by Ruben Quadros on 12/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.InitialStateContent(
    density: Density,
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .animateEnterExit(
                enter = slideInVerticallyAnim(
                    offset = with(density) { -100.dp.roundToPx() },
                    duration = 600
                ) + fadeInAnim(),
                exit = slideOutVerticallyAnim(
                    offset = with(density) { 100.dp.roundToPx() },
                    duration = 300
                ) + fadeOutAnim()
            )
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.select_team_title),
            style = FootieScoreTheme.typography.button,
            color = FootieScoreTheme.colors.surface,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onSkipClick.invoke()
                },
            text = stringResource(id = R.string.select_team_skip),
            style = FootieScoreTheme.typography.button,
            color = FootieScoreTheme.colors.disabled,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(name = "Initial State")
@Composable
fun PreviewInitialStateContent() {
    AnimatedVisibility(visible = true) {
        InitialStateContent(density = LocalDensity.current) {

        }
    }
}