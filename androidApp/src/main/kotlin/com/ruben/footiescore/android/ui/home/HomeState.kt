package com.ruben.footiescore.android.ui.home

import com.ruben.footiescore.entity.AllCompetitionEntity

/**
 * Created by Ruben Quadros on 17/10/21
 **/
sealed class HomeState {
    object InitialState: HomeState()
    data class AllCompetitionsState(val competitions: List<AllCompetitionEntity>): HomeState()
    object ErrorState: HomeState()
}
