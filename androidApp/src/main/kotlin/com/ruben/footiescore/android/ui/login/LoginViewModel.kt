package com.ruben.footiescore.android.ui.login

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.usecase.LoginUseCase
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
        loginUseCase.invoke(LoginUseCase.RequestValue(id, name, email, image)).collect { entity ->
            when (entity) {
                is BaseEntity.Loading -> reduce { LoginState.LoadingState }
                is BaseEntity.Success -> reduce { LoginState.LoginSuccessState }.also { postSideEffect(LoginSideEffect.LoginSuccess) }
                else -> reduce { LoginState.InitialState }.also { postSideEffect(LoginSideEffect.LoginError) }
            }
        }
    }

    fun handleGoogleLoginError() = intent {
        postSideEffect(LoginSideEffect.LoginError)
    }
}