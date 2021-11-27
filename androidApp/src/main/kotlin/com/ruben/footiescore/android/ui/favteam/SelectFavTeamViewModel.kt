package com.ruben.footiescore.android.ui.favteam

import com.ruben.footiescore.android.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import org.orbitmvi.orbit.syntax.simple.intent

/**
 * Created by Ruben Quadros on 27/11/21
 **/
class SelectFavTeamViewModel: BaseViewModel<SelectFavTeamState, SelectFavTeamSideEffect>() {

    private val searchFlow: MutableSharedFlow<String> = MutableSharedFlow()

    override fun createInitialState(): SelectFavTeamState = SelectFavTeamState.InitialState

    override fun initData() {
        super.initData()
    }

    private fun handleSearchTeamsInternal() = intent {
        searchFlow.debounce {
            if (it.length > 3) 300 else 0
        }.collect {

        }
    }

}