package com.ruben.footiescore.android.ui.home

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.usecase.GetLoginStateUseCase
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 28/11/21
 **/
class HomeViewModel(private val loginStateUseCase: GetLoginStateUseCase): BaseViewModel<HomeState, HomeSideEffect>() {

    override fun createInitialState(): HomeState = HomeState.InitialState

    override fun initData() {
        super.initData()
        getDashboardInternal()
    }

    private fun getDashboardInternal() = intent {
        val isLoggedIn = loginStateUseCase.invoke(Unit)
        if (isLoggedIn) {
            //get user data
        } else {
            reduce { HomeState.DashBoardState(isUserLoggedIn = false) }
        }
    }
}