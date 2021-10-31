package com.ruben.footiescore.android.ui.login

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.BottomWiggleShape
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim

/**
 * Created by Ruben Quadros on 31/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LoginScreen() {
    val density = LocalDensity.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.football_kick))

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            backgroundColor = FootieScoreTheme.colors.primary,
            elevation = 20.dp,
            shape = BottomWiggleShape()
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.login_title),
                    style = FootieScoreTheme.typography.title1,
                    color = FootieScoreTheme.colors.onPrimary
                )

                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(elevation = 10.dp)
                    .animateEnterExit(
                        enter = slideInVerticallyAnim(
                            offset = with(density) { 50.dp.roundToPx() },
                            duration = 600
                        ),
                        exit = slideOutVerticallyAnim(offset = with(density) { 50.dp.roundToPx() })
                    ),
                shape = FootieScoreTheme.shapes.largeRoundCornerShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = FootieScoreTheme.colors.secondary,
                    contentColor = FootieScoreTheme.colors.onPrimary
                ),
                onClick = { }
            ) {
                Text(
                    text = stringResource(id = R.string.login_google),
                    style = FootieScoreTheme.typography.button
                )
            }
            Text(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .align(Alignment.CenterHorizontally)
                    .animateEnterExit(
                        enter = slideInVerticallyAnim(
                            offset = with(density) { 24.dp.roundToPx() },
                            duration = 600
                        ),
                        exit = slideOutVerticallyAnim(
                            offset = with(density) { 24.dp.roundToPx() },
                            duration = 600
                        )
                    )
                    .clickable {
                    },
                text = stringResource(id = R.string.login_skip),
                style = FootieScoreTheme.typography.button,
                color = FootieScoreTheme.colors.disabled,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimatedVisibilityScope.PreviewLoginScreen() {
    LoginScreen()
}