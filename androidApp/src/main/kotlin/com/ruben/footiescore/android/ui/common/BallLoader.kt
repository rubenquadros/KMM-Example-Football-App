package com.ruben.footiescore.android.ui.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 17/10/21
 **/
@Composable
fun BallLoader(modifier: Modifier = Modifier, ballColor: Color = FootieScoreTheme.colors.onPrimary) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = modifier.graphicsLayer {
        rotationZ = angle
    }) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_football_white),
            colorFilter = ColorFilter.tint(ballColor),
            contentDescription = stringResource(id = R.string.all_loading)
        )
    }
}

@Preview
@Composable
fun PreviewBallLoader() {
    BallLoader(modifier = Modifier.size(40.dp))
}