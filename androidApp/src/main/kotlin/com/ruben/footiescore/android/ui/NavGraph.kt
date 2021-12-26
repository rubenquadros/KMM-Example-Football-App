package com.ruben.footiescore.android.ui

import androidx.navigation.NavHostController
import com.ruben.footiescore.android.ui.Destinations.Landing
import com.ruben.footiescore.android.ui.Destinations.Login
import com.ruben.footiescore.android.ui.Destinations.SelectTeam

/**
 * Created by Ruben Quadros on 16/10/21
 **/
object Destinations {
    const val Welcome = "welcome"
    const val Login = "login"
    const val SelectTeam = "selectTeam"
    const val Landing = "landing"
}

class NavGraph(navHostController: NavHostController) {

    val openLoginScreen: () -> Unit = {
        navHostController.navigate(route = Login)
    }

    val openSelectTeamScreen: () -> Unit = {
        navHostController.navigate(route = SelectTeam)
    }

    val openLandingScreen: () -> Unit = {
        navHostController.navigate(route = Landing) {
            popUpTo(navHostController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }
}