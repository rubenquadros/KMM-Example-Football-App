package com.ruben.footiescore.android.ui.login

/**
 * Created by Ruben Quadros on 31/10/21
 **/
sealed class LoginState {
    object InitialState: LoginState()
    object LoadingState: LoginState()
    object LoginSuccessState: LoginState()
}
