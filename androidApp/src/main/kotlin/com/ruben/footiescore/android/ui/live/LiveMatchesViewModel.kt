package com.ruben.footiescore.android.ui.live

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.usecase.GetLiveMatchesUseCase
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 09/01/22
 **/
class LiveMatchesViewModel(private val getLiveMatchesUseCase: GetLiveMatchesUseCase): BaseViewModel<LiveMatchesState, LiveMatchesSideEffect>() {

    override fun createInitialState(): LiveMatchesState = LiveMatchesState.InitialState

    override fun initData() {
        super.initData()
        getLiveMatchesInternal()
    }

    private fun getLiveMatchesInternal() = intent {
        getLiveMatchesUseCase.invoke(Unit).collect { entity ->
            reduce {
                when (entity) {
                    is BaseEntity.Loading -> {
                        state.handleLoading(shouldShow = true)
                        state
                    }
                    is BaseEntity.Success -> {
                        state.handleLoading(shouldShow = false)
                        if (entity.body.isEmpty()) {
                            LiveMatchesState.NoLiveMatchesState
                        } else {
                            LiveMatchesState.MatchesState(entity.body)
                        }
                    }
                    else -> {
                        state.handleLoading(shouldShow = false)
                        LiveMatchesState.ErrorState
                    }
                }
            }
        }
    }
}