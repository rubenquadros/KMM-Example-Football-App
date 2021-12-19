package com.ruben.footiescore.android.ui.home

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.entity.UserEntity
import com.ruben.footiescore.core.domain.usecase.GetLoginStateUseCase
import com.ruben.footiescore.core.domain.usecase.GetRecentMatchesUseCase
import com.ruben.footiescore.core.domain.usecase.GetUserDataUseCase
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 28/11/21
 **/
class HomeViewModel(
    private val loginStateUseCase: GetLoginStateUseCase,
    private val getRecentMatchesUseCase: GetRecentMatchesUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : BaseViewModel<HomeState, HomeSideEffect>() {

    override fun createInitialState(): HomeState = HomeState.InitialState

    override fun initData() {
        super.initData()
        getDashboardInternal()
    }

    private fun getDashboardInternal() = intent {
        val isLoggedIn = loginStateUseCase.invoke(Unit)
        var userDetails: UserEntity? = null
        if (isLoggedIn) {
            //get user data
            getUserDataUseCase.invoke(Unit).collect { entity ->
                if (entity is BaseEntity.Success) {
                    userDetails = entity.body
                }
            }
        }
        //get recent matches
        getRecentMatchesUseCase.invoke(Unit).collect { entity ->
            reduce {
                when (entity) {
                    is BaseEntity.Loading -> {
                        HomeState.LoadingState
                    }
                    is BaseEntity.Success -> {
                        HomeState.DashBoardState(
                            isUserLoggedIn = isLoggedIn,
                            teamMatchesDetails = entity.body,
                            userDetails = userDetails
                        )
                    }
                    else -> {
                        HomeState.ErrorState
                    }
                }
            }
        }
    }
}