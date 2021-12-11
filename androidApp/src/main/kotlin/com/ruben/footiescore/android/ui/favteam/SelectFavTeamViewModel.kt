package com.ruben.footiescore.android.ui.favteam

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.core.domain.usecase.SearchTeamUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

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
            searchTeamUseCase.invoke(SearchTeamUseCase.RequestValue(it)).collect { result ->
                when (result) {
                    is com.ruben.footiescore.shared.domain.entity.BaseEntity.Success -> {
                        if (result.body.isEmpty()) {
                            reduce { SelectFavTeamState.NoResultsState }
                        } else {
                            reduce { SelectFavTeamState.SearchResultState(result.body) }
                        }
                    }
                    else -> {
                        if ((state as? SelectFavTeamState.SearchResultState)?.searchResults?.isEmpty() == true) {
                            reduce { SelectFavTeamState.ErrorState }
                        } else {
                            postSideEffect(SelectFavTeamSideEffect.ShowErrorMessage)
                        }
                    }
                }
            }
        }
    }

}