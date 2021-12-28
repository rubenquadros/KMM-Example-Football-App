package com.ruben.footiescore.android.ui.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.base.theme.Gray100
import com.ruben.footiescore.android.ui.common.ErrorView
import com.ruben.footiescore.android.ui.common.PitchLoader
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import com.ruben.footiescore.android.ui.profile.component.PersonalDetailsContent
import com.ruben.footiescore.android.ui.profile.component.TeamDetailsContent
import com.ruben.footiescore.core.domain.entity.ProfileEntity
import com.ruben.footiescore.core.domain.entity.TeamEntity
import com.ruben.footiescore.core.domain.entity.UserEntity

/**
 * Created by Ruben Quadros on 26/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.ProfileScreen(
    profileViewModel: ProfileViewModel
) {

    val density = LocalDensity.current

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = profileViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = profileViewModel.createInitialState())

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is ProfileState.ProfileDetailsState -> {
                val profileDetailsState = state as? ProfileState.ProfileDetailsState
                profileDetailsState?.let {
                    ProfileContent(
                        density = density,
                        profileEntity = it.profileEntity
                    )
                }
            }

            is ProfileState.ErrorState -> {
                ErrorView(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.Center),
                    errorMessage = stringResource(id = R.string.all_generic_error),
                    enterTransition = slideInVerticallyAnim(
                        offset = with(density) { -100.dp.roundToPx() },
                        duration = 600
                    ) + fadeIn(),
                    exitTransition = slideOutVerticallyAnim(
                        offset = with(density) { 100.dp.roundToPx() },
                        duration = 300
                    ) + fadeOut(),
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
    profileEntity: ProfileEntity
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .animateEnterExit(
                enter = slideInVerticallyAnim(
                    offset = with(density) { -100.dp.roundToPx() },
                    duration = 600
                ) + fadeIn(),
                exit = slideOutVerticallyAnim(
                    offset = with(density) { 100.dp.roundToPx() },
                    duration = 300
                ) + fadeOut()
            )
    ) {
        PersonalDetailsContent(profileEntity.userEntity)

        TeamDetailsContent(profileEntity.teamEntity)

        LogoutButton(modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun LogoutButton(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .padding(16.dp),
        shape = FootieScoreTheme.shapes.largeRoundCornerShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Gray100,
            contentColor = FootieScoreTheme.colors.surface
        ),
        onClick = {  },
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

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewProfileContent(@PreviewParameter(ProfileEntityProvider::class) profileEntity: ProfileEntity) {
    AnimatedVisibility(visible = true) {
        ProfileContent(
            modifier = Modifier.background(FootieScoreTheme.colors.onPrimary),
            density = LocalDensity.current,
            profileEntity = profileEntity
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