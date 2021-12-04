package com.ruben.footiescore.android.ui.favteam

import com.ruben.footiescore.entity.SearchTeamEntity

/**
 * Created by Ruben Quadros on 27/11/21
 **/
sealed class SelectFavTeamState {
    object InitialState: SelectFavTeamState()
    object LoadingState: SelectFavTeamState()
    data class SearchResultState(val searchResults: List<SearchTeamEntity>): SelectFavTeamState()
    object NoResultsState: SelectFavTeamState()
    object ErrorState: SelectFavTeamState()
}