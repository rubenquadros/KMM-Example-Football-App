package com.ruben.footiescore.android.ui.competitions

import com.ruben.footiescore.entity.AllCompetitionEntity

/**
 * Created by Ruben Quadros on 17/10/21
 **/
sealed class CompetitionsState {
    object InitialState: CompetitionsState()
    object LoadingState: CompetitionsState()
    data class AllCompetitionsState(val competitions: List<AllCompetitionEntity>): CompetitionsState()
    object ErrorState: CompetitionsState()
}
