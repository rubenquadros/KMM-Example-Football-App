package com.ruben.footiescore.android.ui

import androidx.navigation.NavHostController
import com.ruben.footiescore.android.ui.Destinations.Login

/**
 * Created by Ruben Quadros on 16/10/21
 **/
object Destinations {
    const val Home = "home"
    const val Welcome = "welcome"
    const val Login = "login"
}

class NavGraph(navHostController: NavHostController) {

    val openLoginScreen: () -> Unit = {
        navHostController.navigate(route = Login)
    }
}