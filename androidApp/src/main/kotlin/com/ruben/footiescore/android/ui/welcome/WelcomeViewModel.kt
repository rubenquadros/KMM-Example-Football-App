package com.ruben.footiescore.android.ui.welcome

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.usecase.StoreFirstTimeLaunchUseCase
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

/**
 * Created by Ruben Quadros on 30/10/21
 **/
class WelcomeViewModel(private val storeFirstTimeLaunchUseCase: StoreFirstTimeLaunchUseCase) :
    BaseViewModel<WelcomeState, WelcomeSideEffect>() {

    override fun createInitialState(): WelcomeState = WelcomeState.InitialState

    fun storeFirstTime() = intent {
        storeFirstTimeLaunchUseCase.invoke(StoreFirstTimeLaunchUseCase.RequestValue(false))
        postSideEffect(WelcomeSideEffect.NavigateToLogin)
    }

}