package com.ruben.footiescore.android.ui.favteam

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.usecase.SearchTeamUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import org.orbitmvi.orbit.syntax.simple.intent

/**
 * Created by Ruben Quadros on 27/11/21
 **/
class SelectFavTeamViewModel(private val searchTeamUseCase: SearchTeamUseCase): BaseViewModel<SelectFavTeamState, SelectFavTeamSideEffect>() {

    private val searchFlow: MutableSharedFlow<String> = MutableSharedFlow()

    override fun createInitialState(): SelectFavTeamState = SelectFavTeamState.InitialState

    override fun initData() {
        super.initData()
        handleSearchTeamsInternal()
    }

    fun searchTeam(searchQuery: String) = intent {
        searchFlow.emit(searchQuery)
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchTeamsInternal() = intent {
        searchFlow.debounce {
            if (it.length > 3) 300 else 0
        }.collect {
            searchTeamUseCase.invoke(SearchTeamUseCase.RequestValue(it))
        }
    }

}