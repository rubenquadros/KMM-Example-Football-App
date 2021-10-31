package com.ruben.footiescore.android.ui.welcome

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.usecase.StoreFirstTimeLaunchUseCase
import org.orbitmvi.orbit.syntax.simple.intent

/**
 * Created by Ruben Quadros on 30/10/21
 **/
class WelcomeViewModel(private val storeFirstTimeLaunchUseCase: StoreFirstTimeLaunchUseCase) : BaseViewModel<WelcomeState, WelcomeSideEffect>() {

    override fun createInitialState(): WelcomeState = WelcomeState.InitialState

    fun storeFirstTime() = intent {

    }

}