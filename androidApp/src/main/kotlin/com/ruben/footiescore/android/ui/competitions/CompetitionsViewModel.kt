package com.ruben.footiescore.android.ui.competitions

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.usecase.GetAllCompetitionsUseCase
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class CompetitionsViewModel(
    private val useCase: GetAllCompetitionsUseCase
): BaseViewModel<CompetitionsState, CompetitionsSideEffect>() {

    override fun createInitialState(): CompetitionsState = CompetitionsState.InitialState

    override fun initData() {
        super.initData()
        getAllCompetitions()
    }

    private fun getAllCompetitions() = intent {
        useCase.invoke(Unit).collect { result ->
            reduce {
                when (result) {
                    is BaseEntity.Loading -> CompetitionsState.LoadingState
                    is BaseEntity.Success -> CompetitionsState.AllCompetitionsState(result.body)
                    else -> CompetitionsState.ErrorState
                }
            }
        }
    }
}