package com.ruben.footiescore.android.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.ruben.footiescore.android.ui.common.PitchLoader
import com.ruben.footiescore.android.ui.home.component.UserDetailsContent
import com.ruben.footiescore.core.domain.entity.UserEntity
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 28/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.HomeScreen(
    homeViewModel: HomeViewModel = getViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (state is HomeState.DashBoardState) {
                val dashBoardState = state as? HomeState.DashBoardState
                dashBoardState?.let {
                    DashboardContent(
                        scrollState = scrollState,
                        isUserLoggedIn = it.isUserLoggedIn,
                        userDetails = it.userDetails,
                        teamMatches = it.teamMatchesDetails
                    )
                }
            }

            PitchLoader(
                modifier = Modifier.fillMaxSize(),
                isVisible = state is HomeState.LoadingState
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.DashboardContent(
    scrollState: ScrollState,
    isUserLoggedIn: Boolean,
    userDetails: UserEntity?,
    teamMatches: Any?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        UserDetailsContent(isUserLoggedIn, userDetails)

        teamMatches?.let {
            //show team matches
        }


    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewDashboardContent() {
    AnimatedVisibility(visible = true) {
        DashboardContent(
            scrollState = rememberScrollState(),
            isUserLoggedIn = true,
            userDetails = UserEntity("123", "Ruben Quadros", "", "", -1),
            teamMatches = null
        )
    }
}