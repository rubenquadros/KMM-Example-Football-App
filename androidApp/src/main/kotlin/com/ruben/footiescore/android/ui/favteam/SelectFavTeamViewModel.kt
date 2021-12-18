package com.ruben.footiescore.android.ui.favteam

import com.ruben.footiescore.android.ui.base.BaseViewModel
import com.ruben.footiescore.core.domain.entity.SearchTeamEntity
import com.ruben.footiescore.core.domain.usecase.SaveTeamUseCase
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
class SelectFavTeamViewModel(
    private val searchTeamUseCase: SearchTeamUseCase,
    private val saveTeamUseCase: SaveTeamUseCase,
) : BaseViewModel<SelectFavTeamState, SelectFavTeamSideEffect>() {

    private val searchFlow: MutableSharedFlow<String> = MutableSharedFlow()
    private var searchResults: MutableList<SearchTeamEntity> = mutableListOf()

    override fun createInitialState(): SelectFavTeamState = SelectFavTeamState.InitialState

    override fun initData() {
        super.initData()
        handleSearchTeamsInternal()
    }

    fun searchTeam(searchQuery: String) = intent {
        searchFlow.emit(searchQuery)
    }

    fun onSearchCleared() = intent {
        reduce { SelectFavTeamState.InitialState }
    }

    fun saveTeam(id: Int) = intent {
        saveTeamUseCase.invoke(SaveTeamUseCase.RequestValue(id)).collect { result ->
            when (result) {
                is BaseEntity.Loading -> reduce {
                    state.handleLoading(shouldShow = true)
                    state
                }
                is BaseEntity.SuccessNoBody -> postSideEffect(SelectFavTeamSideEffect.SelectTeamSuccess)
                is BaseEntity.ForbiddenAction -> {
                    //already fav team
                    reduce {
                        state.handleLoading(shouldShow = false)
                        state
                    }.also {
                        intent { postSideEffect(SelectFavTeamSideEffect.TeamAlreadySelected) }
                    }
                }
                else -> {
                    reduce {
                        state.handleLoading(shouldShow = false)
                        state
                    }.also {
                        intent { postSideEffect(SelectFavTeamSideEffect.ShowErrorMessage) }
                    }
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchTeamsInternal() = intent {
        searchFlow.debounce {
            if (it.length > 3) 300 else 0
        }.collect {
            searchTeamUseCase.invoke(SearchTeamUseCase.RequestValue(it)).collect { result ->
                reduce {
                    when (result) {
                        is BaseEntity.Loading -> {
                            state.handleLoading(shouldShow = true)
                            state
                        }
                        is BaseEntity.Success -> {
                            state.handleLoading(shouldShow = false)
                            if (result.body.isEmpty()) {
                                searchResults.clear()
                                SelectFavTeamState.NoResultsState
                            } else {
                                searchResults = result.body.toMutableList()
                                SelectFavTeamState.SearchResultState(result.body)
                            }
                        }
                        else -> {
                            state.handleLoading(shouldShow = false)
                            if (searchResults.isEmpty()) {
                                SelectFavTeamState.ErrorState.also {
                                    intent { postSideEffect(SelectFavTeamSideEffect.HideKeyboard) }
                                }
                            } else {
                                state.also { intent { postSideEffect(SelectFavTeamSideEffect.ShowErrorMessage) } }
                            }
                        }
                    }
                }
            }
        }
    }

}