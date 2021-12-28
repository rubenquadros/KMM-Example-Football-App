package com.ruben.footiescore.android.ui.landing

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.LandingBackHandler
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.competitions.AllCompetitionsScreen
import com.ruben.footiescore.android.ui.competitions.CompetitionsViewModel
import com.ruben.footiescore.android.ui.home.HomeScreen
import com.ruben.footiescore.android.ui.home.HomeViewModel
import com.ruben.footiescore.android.ui.profile.ProfileScreen
import com.ruben.footiescore.android.ui.profile.ProfileViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 25/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LandingScreen(
    landingViewModel: LandingViewModel = getViewModel(),
    homeViewModel: HomeViewModel = getViewModel(),
    competitionsViewModel: CompetitionsViewModel = getViewModel(),
    profileViewModel: ProfileViewModel = getViewModel(),
    navigateToLogin: (isBackAllowed: Boolean) -> Unit
) {

    HandleSideEffects(sideEffectFlow = landingViewModel.uiSideEffect(), navigateToLogin = navigateToLogin)

    val navController = rememberAnimatedNavController()
    val scaffoldState = rememberScaffoldState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = landingViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = landingViewModel.createInitialState())

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            FootieScoreBottomNavigation(
                items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Browse,
                    NavigationItem.Live,
                    NavigationItem.Profile
                ),
                getCurrentScreen = { currentRoute.orEmpty() },
                onClick = { route ->
                    if (route == NavigationItem.PROFILE_ROUTE) {
                        if (state.isLoggedIn.not()) {
                            landingViewModel.navigateToLogin()
                            return@FootieScoreBottomNavigation
                        }
                    }
                    navController.navigate(route = route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        if (route != NavigationItem.LIVE_ROUTE) {
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = NavigationItem.HOME_ROUTE,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = NavigationItem.HOME_ROUTE,
                enterTransition = {
                    fadeInAnim(alpha = 0.4f, duration = 400)
                },
                exitTransition = {
                    fadeOutAnim(alpha = 0.4f, duration = 400)
                }
            ) {
                HomeScreen(homeViewModel = homeViewModel)
            }

            composable(route = NavigationItem.BROWSE_ROUTE) {
                AllCompetitionsScreen(competitionsViewModel = competitionsViewModel)
            }

            composable(route = NavigationItem.LIVE_ROUTE) {
                AllCompetitionsScreen(competitionsViewModel = competitionsViewModel)
            }

            composable(route = NavigationItem.PROFILE_ROUTE) {
                ProfileScreen(profileViewModel = profileViewModel)
            }
        }
    }

    LandingBackHandler()
}

@Composable
fun HandleSideEffects(sideEffectFlow: Flow<LandingSideEffect>, navigateToLogin: (isBackAllowed: Boolean) -> Unit) {
    LaunchedEffect(key1 = sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is LandingSideEffect.NavigateToLogin -> navigateToLogin.invoke(true)
            }
        }
    }
}