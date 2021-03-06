package com.ruben.footiescore.android.ui.login

/**
 * Created by Ruben Quadros on 31/10/21
 **/
sealed class LoginSideEffect {
    object NavigateToHome: LoginSideEffect()
    object NavigateToSelectTeam: LoginSideEffect()
    object LoginError: LoginSideEffect()
}
