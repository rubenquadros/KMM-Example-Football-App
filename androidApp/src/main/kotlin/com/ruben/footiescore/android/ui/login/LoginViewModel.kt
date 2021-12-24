package com.ruben.footiescore.android.ui.login

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.usecase.LoginUseCase
import com.ruben.footiescore.core.domain.usecase.SetLoginStateUseCase
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 * Created by Ruben Quadros on 31/10/21
 **/
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val setLoginStateUseCase: SetLoginStateUseCase
) : BaseViewModel<LoginState, LoginSideEffect>() {

    override fun createInitialState(): LoginState = LoginState.InitialState

    fun login(id: String, name: String, email: String, image: String) = intent {
        loginUseCase.invoke(LoginUseCase.RequestValue(id, name, email, image)).collect { entity ->
            reduce {
                when (entity) {
                    is BaseEntity.Loading -> LoginState.LoadingState
                    is BaseEntity.Success -> {
                        LoginState.LoginSuccessState.also {
                            intent {
                                //setLoginStateUseCase.invoke(SetLoginStateUseCase.RequestValue(isUseLoggedIn = true))
                                if (entity.body.teamId != null) {
                                    postSideEffect(LoginSideEffect.NavigateToHome)
                                } else {
                                    postSideEffect(LoginSideEffect.NavigateToSelectTeam)
                                }
                            }
                        }
                    }
                    else -> LoginState.InitialState.also {
                        intent { postSideEffect(LoginSideEffect.LoginError)  }
                    }
                }
            }
        }
    }

    fun handleGoogleLoginError() = intent {
        postSideEffect(LoginSideEffect.LoginError)
    }
}