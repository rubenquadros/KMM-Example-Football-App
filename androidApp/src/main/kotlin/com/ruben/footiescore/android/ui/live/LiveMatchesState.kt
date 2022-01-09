package com.ruben.footiescore.android.ui.live

import com.ruben.footiescore.core.domain.entity.MatchEntity

/**
 * Created by Ruben Quadros on 09/01/22
 **/
sealed class LiveMatchesState {
    object LoadingState: LiveMatchesState()
    object InitialState: LiveMatchesState()
    object ErrorState: LiveMatchesState()
    object NoLiveMatchesState: LiveMatchesState()
    data class MatchesState(val matches: List<MatchEntity>): LiveMatchesState()
}
