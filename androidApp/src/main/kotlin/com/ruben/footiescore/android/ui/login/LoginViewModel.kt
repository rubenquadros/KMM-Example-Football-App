package com.ruben.footiescore.android.ui.login

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.entity.BaseEntity
import com.ruben.footiescore.usecase.LoginUseCase
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 31/10/21
 **/
class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel<LoginState, LoginSideEffect>() {

    override fun createInitialState(): LoginState = LoginState.InitialState

    fun login(id: String, name: String, email: String, image: String) = intent {
        loginUseCase.invoke(LoginUseCase.RequestValue(id, name, email, image)).collect { result ->
            when (result) {
                is BaseEntity.Loading -> reduce { LoginState.LoadingState }
                is BaseEntity.SuccessNoBody -> postSideEffect(LoginSideEffect.LoginSuccess)
                else -> postSideEffect(LoginSideEffect.LoginError)
            }
        }
    }

    fun handleGoogleLoginError() = intent {  }
}