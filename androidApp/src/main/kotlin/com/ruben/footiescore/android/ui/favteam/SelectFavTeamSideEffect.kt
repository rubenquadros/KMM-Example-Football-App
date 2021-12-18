package com.ruben.footiescore.android.ui.favteam

/**
 * Created by Ruben Quadros on 27/11/21
 **/
sealed class SelectFavTeamSideEffect {
    object ShowErrorMessage: SelectFavTeamSideEffect()
    object HideKeyboard: SelectFavTeamSideEffect()
    object TeamAlreadySelected: SelectFavTeamSideEffect()
    object SelectTeamSuccess: SelectFavTeamSideEffect()
}
