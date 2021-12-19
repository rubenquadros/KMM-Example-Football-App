package com.ruben.footiescore.android.ui.home

import com.ruben.footiescore.core.domain.entity.RecentMatchesEntity
import com.ruben.footiescore.core.domain.entity.UserEntity

/**
 * Created by Ruben Quadros on 28/11/21
 **/
sealed class HomeState {
    object InitialState: HomeState()
    object LoadingState: HomeState()
    object ErrorState: HomeState()
    data class DashBoardState(
        val isUserLoggedIn: Boolean,
        val userDetails: UserEntity? = null,
        val teamMatchesDetails: List<RecentMatchesEntity>
    ) : HomeState()
}