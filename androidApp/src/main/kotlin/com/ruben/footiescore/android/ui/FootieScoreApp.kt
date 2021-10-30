package com.ruben.footiescore.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ruben.footiescore.android.ui.Destinations.Home
import com.ruben.footiescore.android.ui.Destinations.Login
import com.ruben.footiescore.android.ui.Destinations.Welcome
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
                fadeIn(initialAlpha = 0.4f, animationSpec = tween(durationMillis = 800))
            },
            exitTransition = { _, _ ->
                fadeOut(targetAlpha = 0.4f, animationSpec = tween(durationMillis = 800))
            }
        ) {
            WelcomeScreen(navigateToLogin = navGraph.openLoginScreen)
        }

        composable(
            route = Login,
            enterTransition = { _, _ ->
                fadeIn(initialAlpha = 0.4f, animationSpec = tween(durationMillis = 800))
            },
            exitTransition = { _, _ ->
                fadeOut(targetAlpha = 0.4f, animationSpec = tween(durationMillis = 800))
            }
        ) {
            LoginScreen()
        }

        composable(Home) {
            HomeScreen()
        }
    }
}