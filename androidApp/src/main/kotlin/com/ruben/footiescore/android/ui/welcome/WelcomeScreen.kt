package com.ruben.footiescore.android.ui.welcome

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.BottomWiggleShape
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 30/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.WelcomeScreen(
    welcomeViewModel: WelcomeViewModel = getViewModel(),
    navigateToLogin: () -> Unit
) {
    val density = LocalDensity.current

    HandleWelcomeSideEffect(welcomeViewModel.uiSideEffect(), navigateToLogin)

    WelcomeScreenContent(
        density = density,
        onClick = { welcomeViewModel.storeFirstTime() }
    )

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.WelcomeScreenContent(
    density: Density,
    onClick: () -> Unit
) {
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
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(0.4f)
                        .padding(vertical = 16.dp),
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = stringResource(id = R.string.content_description_app_logo)
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.welcome_title),
                    style = FootieScoreTheme.typography.title1.copy(fontSize = 28.sp),
                    color = FootieScoreTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .padding(top = 24.dp, start = 8.dp, end = 8.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.welcome_description),
                    style = FootieScoreTheme.typography.subtitle1,
                    color = FootieScoreTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .shadow(elevation = 10.dp)
                .animateEnterExit(
                    enter = slideInVerticallyAnim(
                        offset = with(density) { -50.dp.roundToPx() },
                        duration = 600
                    ),
                    exit = slideOutVerticallyAnim(offset = with(density) { -50.dp.roundToPx() })
                ),
            shape = FootieScoreTheme.shapes.largeRoundCornerShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = FootieScoreTheme.colors.secondary,
                contentColor = FootieScoreTheme.colors.onPrimary
            ),
            onClick = { onClick.invoke() }
        ) {
            Text(
                text = stringResource(id = R.string.all_get_started),
                style = FootieScoreTheme.typography.button
            )
        }
    }
}

@Composable
fun HandleWelcomeSideEffect(sideEffectFlow: Flow<WelcomeSideEffect>, navigateToLogin: () -> Unit) {
    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect {
            navigateToLogin.invoke()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun AnimatedVisibilityScope.PreviewWelcomeScreen() {
    WelcomeScreenContent(density = LocalDensity.current) {

    }
}