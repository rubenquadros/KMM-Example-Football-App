package com.ruben.footiescore.android.ui.favteam

import com.ruben.footiescore.core.domain.entity.SearchTeamEntity

/**
 * Created by Ruben Quadros on 27/11/21
 **/
sealed class SelectFavTeamState {
    object InitialState: SelectFavTeamState()
    data class LoadingState(val isError: Boolean = false): SelectFavTeamState()
    data class SearchResultState(val searchResults: List<SearchTeamEntity>): SelectFavTeamState()
    object NoResultsState: SelectFavTeamState()
    object ErrorState: SelectFavTeamState()
}

fun SelectFavTeamState.shouldShowLoading(): Boolean {
    return this is SelectFavTeamState.LoadingState && (this as? SelectFavTeamState.LoadingState)?.isError?.not() == true
}