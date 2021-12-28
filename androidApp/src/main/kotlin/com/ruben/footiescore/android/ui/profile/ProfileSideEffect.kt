package com.ruben.footiescore.android.ui.profile

/**
 * Created by Ruben Quadros on 26/12/21
 **/
sealed class ProfileSideEffect {
    object LogoutError: ProfileSideEffect()
    object LogoutSuccess: ProfileSideEffect()
}
