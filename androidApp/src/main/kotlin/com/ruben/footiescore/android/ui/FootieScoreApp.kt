package com.ruben.footiescore.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ruben.footiescore.android.ui.Destinations.Landing
import com.ruben.footiescore.android.ui.Destinations.Login
import com.ruben.footiescore.android.ui.Destinations.LoginArgs.IS_BACK_ALLOWED
import com.ruben.footiescore.android.ui.Destinations.SelectTeam
import com.ruben.footiescore.android.ui.Destinations.Welcome
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.favteam.SelectTeamScreen
import com.ruben.footiescore.android.ui.landing.LandingScreen
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
    val home = if (isFirstTime) Welcome else Landing
    AnimatedNavHost(navController = navController, startDestination = home) {
        composable(
            route = Welcome,
            enterTransition = {
                fadeInAnim(alpha = 0.4f, duration = 600)
            },
            exitTransition = {
                fadeOutAnim(alpha = 0.4f, duration = 600)
            }
        ) {
            WelcomeScreen(navigateToLogin = navGraph.openLoginScreen)
        }

        composable(
            route = "$Login/{$IS_BACK_ALLOWED}",
            arguments = listOf(
                navArgument(IS_BACK_ALLOWED) { type = NavType.BoolType }
            ),
            enterTransition = {
                fadeInAnim(alpha = 0.4f, duration = 800)
            },
            exitTransition = {
                fadeOutAnim(alpha = 0.4f, duration = 200)
            }
        ) {
            LoginScreen(
                navigateToSelectTeam = navGraph.openSelectTeamScreen,
                navigateToHome = navGraph.openLandingScreen,
                isBackAllowed = it.arguments?.getBoolean(IS_BACK_ALLOWED) ?: false
            )
        }

        composable(
            route = SelectTeam,
            enterTransition = {
                fadeInAnim(alpha = 0.4f, duration = 400)
            },
            exitTransition = {
                fadeOutAnim(alpha = 0.4f, duration = 600)
            }
        ) {
            SelectTeamScreen(
                navigateToHome = navGraph.openLandingScreen
            )
        }

        composable(
            route = Landing,
            enterTransition = {
                fadeInAnim(alpha = 0.4f, duration = 800)
            },
            exitTransition = {
                fadeOutAnim(alpha = 0.4f, duration = 600)
            }
        ) {
            LandingScreen(
                navigateToLogin = navGraph.openLoginScreen,
                navigateToHome = navGraph.openLandingScreen
            )
        }
    }
}