package com.ruben.footiescore.android.ui.profile

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.usecase.GetUserProfileUseCase
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 26/12/21
 **/
class ProfileViewModel(private val getUserProfileUseCase: GetUserProfileUseCase): BaseViewModel<ProfileState, ProfileSideEffect>() {

    override fun createInitialState(): ProfileState = ProfileState.InitialState

    override fun initData() {
        getUserData()
    }

    private fun getUserData() = intent {
        getUserProfileUseCase.invoke(Unit).collect { entity ->
            reduce {
                when (entity) {
                    is BaseEntity.Loading -> ProfileState.LoadingState
                    is BaseEntity.Success -> ProfileState.ProfileDetailsState(
                        profileEntity = entity.body
                    )
                    else -> ProfileState.ErrorState
                }
            }
        }
    }
}