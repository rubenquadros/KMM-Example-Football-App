package com.ruben.footiescore.android.ui.home

/**
 * Created by Ruben Quadros on 17/10/21
 **/
sealed class HomeState {
    object InitialState: HomeState()
    object DatState: HomeState()
}
