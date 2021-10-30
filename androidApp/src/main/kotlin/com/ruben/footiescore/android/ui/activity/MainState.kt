package com.ruben.footiescore.android.ui.activity

/**
 * Created by Ruben Quadros on 30/10/21
 **/
sealed class MainState {
    object InitialState: MainState()
    data class AppValues(val isFirstTime: Boolean): MainState()
}
