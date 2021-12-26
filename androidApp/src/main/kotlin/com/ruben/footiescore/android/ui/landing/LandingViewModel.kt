package com.ruben.footiescore.android.ui.landing

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.usecase.GetLoginStateUseCase
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 26/12/21
 **/
class LandingViewModel(
    private val loginStateUseCase: GetLoginStateUseCase
) : BaseViewModel<LandingState, LandingSideEffect>() {

    override fun createInitialState(): LandingState = LandingState()

    override fun initData() {
        getLoginStatus()
    }

    fun navigateToLogin() = intent {
        postSideEffect(LandingSideEffect.NavigateToLogin)
    }

    private fun getLoginStatus() = intent {
        val isLoggedIn = loginStateUseCase.invoke(Unit)
        reduce {
            state.copy(isLoggedIn = isLoggedIn)
        }
    }
}