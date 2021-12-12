package com.ruben.footiescore.android.ui.favteam

import com.ruben.footiescore.core.domain.entity.SearchTeamEntity

/**
 * Created by Ruben Quadros on 27/11/21
 **/
sealed class SelectFavTeamState {
    var shouldShowLoading: Boolean = false
        private set
    object InitialState: SelectFavTeamState()
    data class SearchResultState(val searchResults: List<SearchTeamEntity>): SelectFavTeamState()
    object NoResultsState: SelectFavTeamState()
    object ErrorState: SelectFavTeamState()

    internal fun handleLoading(shouldShow: Boolean) {
        this.shouldShowLoading = shouldShow
    }
}