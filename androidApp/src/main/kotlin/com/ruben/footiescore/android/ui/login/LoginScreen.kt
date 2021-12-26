package com.ruben.footiescore.android.ui.login

import android.app.Activity
import android.content.IntentSender
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.ruben.footiescore.android.BuildConfig
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.BallLoader
import com.ruben.footiescore.android.ui.common.BottomWiggleShape
import com.ruben.footiescore.android.ui.common.EmptyBackHandler
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 31/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LoginScreen(
    loginViewModel: LoginViewModel = getViewModel(),
    navigateToSelectTeam: () -> Unit,
    navigateToHome: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val credential = Identity.getSignInClient(context).getSignInCredentialFromIntent(result.data)
                loginViewModel.login(
                    credential.id,
                    credential.displayName ?: credential.givenName ?: credential.id,
                    credential.id,
                    credential.profilePictureUri?.toString().orEmpty()
                )
            }
        }
    )

    fun onLoginClick() {
        val request = GetSignInIntentRequest.builder()
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .build()

        Identity.getSignInClient(context).getSignInIntent(request)
            .addOnSuccessListener {
                try {
                    launcher.launch(IntentSenderRequest.Builder(it).build())
                } catch (e: IntentSender.SendIntentException) {
                    loginViewModel.handleGoogleLoginError()
                }
            }
            .addOnFailureListener {
                loginViewModel.handleGoogleLoginError()
            }
    }

    HandleSideEffects(
        sideEffectFlow = loginViewModel.uiSideEffect(),
        navigateToSelectTeam = navigateToSelectTeam,
        navigateToHome = navigateToHome,
        scaffoldState = scaffoldState
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = loginViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = loginViewModel.createInitialState())

    Box(modifier = Modifier.fillMaxSize()) {
        LoginScreenContent(
            scaffoldState = scaffoldState,
            onLoginClick = { onLoginClick() },
            onSkipClick = navigateToHome
        )

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = state is LoginState.LoadingState
        ) {
            BallLoader(modifier = Modifier.size(40.dp))
        }
    }

    EmptyBackHandler()

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.LoginScreenContent(
    scaffoldState: ScaffoldState,
    onLoginClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    val density = LocalDensity.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.football_kick))

    Scaffold(
        scaffoldState = scaffoldState
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
                    onClick = onLoginClick
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
                            onSkipClick.invoke()
                        },
                    text = stringResource(id = R.string.login_skip),
                    style = FootieScoreTheme.typography.button,
                    color = FootieScoreTheme.colors.disabled,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun HandleSideEffects(
    sideEffectFlow: Flow<LoginSideEffect>,
    navigateToSelectTeam: () -> Unit,
    scaffoldState: ScaffoldState,
    navigateToHome: () -> Unit
) {
    val loginError = stringResource(id = R.string.all_generic_error)

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is LoginSideEffect.NavigateToHome -> navigateToHome.invoke()
                is LoginSideEffect.NavigateToSelectTeam -> navigateToSelectTeam.invoke()
                is LoginSideEffect.LoginError -> {
                    scaffoldState.snackbarHostState.showSnackbar(loginError)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewLoginScreen() {
    AnimatedVisibility(visible = true) {
        LoginScreenContent(
            scaffoldState = rememberScaffoldState(),
            onLoginClick = {},
            onSkipClick = {}
        )
    }
}