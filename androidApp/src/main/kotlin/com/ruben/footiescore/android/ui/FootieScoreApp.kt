package com.ruben.footiescore.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ruben.footiescore.android.ui.Destinations.AllCompetitions
import com.ruben.footiescore.android.ui.Destinations.Home
import com.ruben.footiescore.android.ui.Destinations.Login
import com.ruben.footiescore.android.ui.Destinations.SelectTeam
import com.ruben.footiescore.android.ui.Destinations.Welcome
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.favteam.SelectTeamScreen
import com.ruben.footiescore.android.ui.competitions.AllCompetitionsScreen
import com.ruben.footiescore.android.ui.home.HomeScreen
import com.ruben.footiescore.android.ui.login.LoginScreen
import com.ruben.footiescore.android.ui.welcome.WelcomeScreen

/**
 * Created by Ruben Quadros on 16/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FootieScoreApp(isFirstTime: Boolean) {
    val navController = rememberAnimatedNavController()
    val navGraph = remember(navController) { NavGraph(navController) }
    val home = if (isFirstTime) Welcome else Home
    AnimatedNavHost(navController = navController, startDestination = home) {
        composable(
            route = Welcome,
            enterTransition = { _, _ ->
                fadeInAnim(alpha = 0.4f, duration = 600)
            },
            exitTransition = { _, _ ->
                fadeOutAnim(alpha = 0.4f, duration = 600)
            }
        ) {
            WelcomeScreen(navigateToLogin = navGraph.openLoginScreen)
        }

        composable(
            route = Login,
            enterTransition = { _, _ ->
                fadeInAnim(alpha = 0.4f, duration = 800)
            },
            exitTransition = { _, _ ->
                fadeOutAnim(alpha = 0.4f, duration = 200)
            }
        ) {
            LoginScreen(navigateToSelectTeam = navGraph.openSelectTeamScreen)
        }

        composable(
            route = SelectTeam,
            enterTransition = { _, _ ->
                fadeInAnim(alpha = 0.4f, duration = 400)
            },
            exitTransition = { _, _ ->
                fadeOutAnim(alpha = 0.4f, duration = 600)
            }
        ) {
            SelectTeamScreen(
                onSelectTeamSuccess = navGraph.openHomeScreen
            )
        }

        composable(
            route = Home,
            enterTransition = { _, _ ->
                fadeInAnim(alpha = 0.4f, duration = 800)
            },
            exitTransition = { _, _ ->
                fadeOutAnim(alpha = 0.4f, duration = 600)
            }
        ) {
            HomeScreen()
        }

        composable(AllCompetitions) {
            AllCompetitionsScreen()
        }
    }
}