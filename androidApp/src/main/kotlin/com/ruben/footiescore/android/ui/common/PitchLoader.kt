package com.ruben.footiescore.android.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 17/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PitchLoader(modifier: Modifier = Modifier, isVisible: Boolean) {
    val density = LocalDensity.current

    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                modifier = Modifier.weight(1f),
                visible = isVisible,
                exit = slideOutVertically(animationSpec = tween(durationMillis = 2000), targetOffsetY = { with(density) { -150.dp.roundToPx() } }) + fadeOut(animationSpec = tween(durationMillis = 2000))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(FootieScoreTheme.colors.primary)
                        .border(2.dp, FootieScoreTheme.colors.onPrimary)
                )
            }

            AnimatedVisibility(
                modifier = Modifier.weight(1f),
                visible = isVisible,
                exit = slideOutVertically(animationSpec = tween(durationMillis = 2000), targetOffsetY = { with(density) { 150.dp.roundToPx() } }) + fadeOut(animationSpec = tween(durationMillis = 2000))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(FootieScoreTheme.colors.primary)
                        .border(2.dp, FootieScoreTheme.colors.onPrimary)
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = isVisible,
            exit = fadeOut()
        ) {
            BallLoader(modifier = Modifier.size(40.dp))
        }
    }
}

@Preview(name = "Pitch loading")
@Composable
fun PreviewPitchLoader() {
    PitchLoader(modifier = Modifier.fillMaxSize(), isVisible = true)
}