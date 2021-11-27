package com.ruben.footiescore.android.ui.favteam

/**
 * Created by Ruben Quadros on 27/11/21
 **/
sealed class SelectFavTeamState {
    object InitialState: SelectFavTeamState()
    object LoadingState: SelectFavTeamState()
    data class SearchResultState(val searchResults: List<String>): SelectFavTeamState()
}