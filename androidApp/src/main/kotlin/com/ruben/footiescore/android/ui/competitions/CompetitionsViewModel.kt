package com.ruben.footiescore.android.ui.competitions

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.usecase.GetAllCompetitionsUseCase
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class CompetitionsViewModel(
    private val getAllCompetitionsUseCase: GetAllCompetitionsUseCase
): BaseViewModel<CompetitionsState, CompetitionsSideEffect>() {

    override fun createInitialState(): CompetitionsState = CompetitionsState.InitialState

    override fun initData() {
        super.initData()
        getAllCompetitions()
    }

    private fun getAllCompetitions() = intent {
        getAllCompetitionsUseCase.invoke(Unit).collect { result ->
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