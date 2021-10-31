package com.ruben.footiescore.android.ui.home

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.usecase.GetAllCompetitionsUseCase
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class HomeViewModel(
    private val useCase: GetAllCompetitionsUseCase
): BaseViewModel<HomeState, HomeSideEffect>() {

    override fun createInitialState(): HomeState = HomeState.InitialState

    override fun initData() {
        super.initData()
        getAllCompetitions()
    }

    private fun getAllCompetitions() = intent {
        useCase.invoke(Unit).collect { result ->
            reduce {
                when (result) {
                    is BaseEntity.Loading -> HomeState.LoadingState
                    is BaseEntity.Success ->  HomeState.AllCompetitionsState(result.body)
                    else -> HomeState.ErrorState
                }
            }
        }
    }
}