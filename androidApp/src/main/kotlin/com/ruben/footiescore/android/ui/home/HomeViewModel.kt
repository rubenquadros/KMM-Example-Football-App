package com.ruben.footiescore.android.ui.home

import androidx.lifecycle.viewModelScope
import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.usecase.GetAllCompetitionsUseCase
import kotlinx.coroutines.launch

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class HomeViewModel(
    private val useCase: GetAllCompetitionsUseCase
): BaseViewModel<HomeState, HomeSideEffect>() {

    override fun createInitialState(): HomeState = HomeState.InitialState

    override fun initData() {
        super.initData()
        getAllCompetitions()
    }

    private fun getAllCompetitions() {
        viewModelScope.launch { useCase.invoke(Unit) }
    }
}