package com.ruben.footiescore.android.ui.activity

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.usecase.GetFirstTimeLaunchUseCase
import org.koin.java.KoinJavaComponent.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce


/**
 * Created by Ruben Quadros on 30/10/21
 **/
class MainViewModel : BaseViewModel<MainState, MainSideEffect>() {

    private val getFirstTimeLaunchUseCase: GetFirstTimeLaunchUseCase by inject(GetFirstTimeLaunchUseCase::class.java)

    override fun createInitialState(): MainState = MainState.InitialState

    fun getIsFirstTimeLaunch() = intent {
        val result = getFirstTimeLaunchUseCase.invoke(Unit)
        reduce {
            MainState.AppValues(isFirstTime = result)
        }
    }
}