package com.ruben.footiescore.android.ui.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.android.gms.auth.api.identity.Identity
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.base.theme.Gray100
import com.ruben.footiescore.android.ui.common.ErrorView
import com.ruben.footiescore.android.ui.common.PitchLoader
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import com.ruben.footiescore.android.ui.profile.component.PersonalDetailsContent
import com.ruben.footiescore.android.ui.profile.component.TeamDetailsContent
import com.ruben.footiescore.core.domain.entity.ProfileEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity
import com.ruben.footiescore.core.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * Created by Ruben Quadros on 26/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.ProfileScreen(
    profileViewModel: ProfileViewModel,
    scaffoldState: ScaffoldState,
    navigateToHome: () -> Unit,
) {

    HandleSideEffects(
        sideEffectFlow = profileViewModel.uiSideEffect(),
        scaffoldState = scaffoldState,
        navigateToHome = navigateToHome
    )

    val context = LocalContext.current
    val density = LocalDensity.current

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = profileViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = profileViewModel.createInitialState())

    fun onLogoutClick() {
        Identity.getSignInClient(context).signOut()
            .addOnSuccessListener {
                profileViewModel.handleLogout(isLogoutSuccess = true)
            }
            .addOnFailureListener {
                profileViewModel.handleLogout(isLogoutSuccess = false)
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is ProfileState.ProfileDetailsState -> {
                val profileDetailsState = state as? ProfileState.ProfileDetailsState
                profileDetailsState?.let {
                    ProfileContent(
                        density = density,
                        profileEntity = it.profileEntity,
                        onLogoutClick = { onLogoutClick() }
                    )
                }
            }

            is ProfileState.ErrorState -> {
                ErrorView(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.Center),
                    errorMessage = stringResource(id = R.string.profile_error),
                    enterTransition = slideInVerticallyAnim(
                        offset = with(density) { -100.dp.roundToPx() },
                        duration = 600
                    ) + fadeInAnim(),
                    exitTransition = slideOutVerticallyAnim(
                        offset = with(density) { 100.dp.roundToPx() },
                        duration = 300
                    ) + fadeOutAnim(),
                    onClick = { profileViewModel.initData() }
                )
            }
            else -> { /*do nothing*/
            }
        }

        PitchLoader(
            modifier = Modifier.fillMaxSize(),
            isVisible = state is ProfileState.LoadingState
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun AnimatedVisibilityScope.ProfileContent(
    modifier: Modifier = Modifier,
    density: Density,
    profileEntity: ProfileEntity,
    onLogoutClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
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
        PersonalDetailsContent(profileEntity.userEntity)

        TeamDetailsContent(profileEntity.teamEntity)

        LogoutButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onLogoutClick = onLogoutClick
        )
    }
}

@Composable
fun LogoutButton(modifier: Modifier = Modifier, onLogoutClick: () -> Unit) {
    Button(
        modifier = modifier
            .padding(16.dp),
        shape = FootieScoreTheme.shapes.largeRoundCornerShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Gray100,
            contentColor = FootieScoreTheme.colors.surface
        ),
        onClick = { onLogoutClick.invoke() },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = stringResource(id = R.string.all_logout),
            tint = FootieScoreTheme.colors.onSurface
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = stringResource(id = R.string.all_logout),
            style = FootieScoreTheme.typography.button,
            color = FootieScoreTheme.colors.surface
        )
    }
}

@Composable
fun HandleSideEffects(
    sideEffectFlow: Flow<ProfileSideEffect>,
    scaffoldState: ScaffoldState,
    navigateToHome: () -> Unit
) {
    val logoutError = stringResource(id = R.string.profile_logout_error)
    LaunchedEffect(key1 = sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is ProfileSideEffect.LogoutError -> {
                    scaffoldState.snackbarHostState.showSnackbar(logoutError)
                }
                is ProfileSideEffect.LogoutSuccess -> {
                    navigateToHome.invoke()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewProfileContent(@PreviewParameter(ProfileEntityProvider::class) profileEntity: ProfileEntity) {
    AnimatedVisibility(visible = true) {
        ProfileContent(
            modifier = Modifier.background(FootieScoreTheme.colors.onPrimary),
            density = LocalDensity.current,
            profileEntity = profileEntity,
            onLogoutClick = {}
        )
    }
}

class ProfileEntityProvider : PreviewParameterProvider<ProfileEntity> {
    override val values: Sequence<ProfileEntity> =
        sequenceOf(
            ProfileEntity(
                userEntity = UserEntity(
                    id = "",
                    name = "Ruben Quadros",
                    email = "rquadros95@gmail.com",
                    profilePic = "",
                    teamId = null
                ),
                teamEntity = null
            ),
            ProfileEntity(
                userEntity = UserEntity(
                    id = "",
                    name = "Ruben Quadros",
                    email = "rquadros95@gmail.com",
                    profilePic = "",
                    teamId = 66
                ),
                teamEntity = TeamEntity(
                    id = 66,
                    name = "Manchester United FC",
                    crestUrl = "",
                    shortName = "MUN"
                )
            )
        )

}