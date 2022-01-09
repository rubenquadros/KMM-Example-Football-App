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

    fun getLiveMatchesInternal(isFirstTimeLoad: Boolean = true) = intent {
        getLiveMatchesUseCase.invoke(GetLiveMatchesUseCase.RequestValue(isFirstTimeLoad)).collect { entity ->
            reduce {
                when (entity) {
                    is BaseEntity.Loading -> {
                        LiveMatchesState.LoadingState
                    }
                    is BaseEntity.Success -> {
                        if (entity.body.isEmpty()) {
                            LiveMatchesState.NoLiveMatchesState
                        } else {
                            LiveMatchesState.MatchesState(entity.body)
                        }
                    }
                    else -> {
                        LiveMatchesState.ErrorState
                    }
                }
            }
        }
    }
}