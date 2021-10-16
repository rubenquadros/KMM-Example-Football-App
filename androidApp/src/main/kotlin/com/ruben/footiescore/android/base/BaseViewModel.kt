package com.ruben.footiescore.android.base

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

/**
 * Created by Ruben Quadros on 16/10/21
 **/
abstract class BaseViewModel<STATE : Any, SIDE_EFFECT: Any>: ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

    override val container: Container<STATE, SIDE_EFFECT> by lazy {
        container(initialState = createInitialState()) {
            initData()
        }
    }

    open fun initData() = intent {  }

    abstract fun createInitialState(): STATE

    fun uiState() = container.stateFlow

    fun uiSideEffect() = container.sideEffectFlow
}