package com.ruben.footiescore.android.ui.landing

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ruben.footiescore.android.ui.common.LandingBackHandler
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.competitions.AllCompetitionsScreen
import com.ruben.footiescore.android.ui.competitions.CompetitionsViewModel
import com.ruben.footiescore.android.ui.home.HomeScreen
import com.ruben.footiescore.android.ui.home.HomeViewModel
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 25/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LandingScreen(
    homeViewModel: HomeViewModel = getViewModel(),
    competitionsViewModel: CompetitionsViewModel = getViewModel(),
    navigateToLogin: () -> Unit
) {

    val navController = rememberAnimatedNavController()
    val scaffoldState = rememberScaffoldState()

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
                AllCompetitionsScreen(competitionsViewModel = competitionsViewModel)
            }
        }
    }

    LandingBackHandler()
}