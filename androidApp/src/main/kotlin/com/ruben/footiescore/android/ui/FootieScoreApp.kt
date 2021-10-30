package com.ruben.footiescore.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ruben.footiescore.android.ui.Destinations.Home
import com.ruben.footiescore.android.ui.Destinations.Welcome
import com.ruben.footiescore.android.ui.home.HomeScreen
import com.ruben.footiescore.android.ui.welcome.WelcomeScreen

/**
 * Created by Ruben Quadros on 16/10/21
 **/
@Composable
fun FootieScoreApp(isFirstTime: Boolean) {
    val navController = rememberNavController()
    val navGraph = remember(navController) { NavGraph(navController) }
    val home = if (isFirstTime) Welcome else Home
    NavHost(navController = navController, startDestination = home) {
        composable(Welcome) {
            WelcomeScreen()
        }

        composable(Home) {
            HomeScreen()
        }
    }
}