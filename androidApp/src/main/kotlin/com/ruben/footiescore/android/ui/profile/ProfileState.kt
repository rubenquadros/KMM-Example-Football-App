package com.ruben.footiescore.android.ui.profile

import com.ruben.footiescore.core.domain.entity.ProfileEntity

/**
 * Created by Ruben Quadros on 26/12/21
 **/
sealed class ProfileState {
    object InitialState: ProfileState()
    object LoadingState: ProfileState()
    data class ProfileDetailsState(val profileEntity: ProfileEntity): ProfileState()
    object ErrorState: ProfileState()
}
