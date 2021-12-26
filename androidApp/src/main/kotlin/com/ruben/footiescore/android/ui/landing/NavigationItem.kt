package com.ruben.footiescore.android.ui.landing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ruben.footiescore.android.R

/**
 * Created by Ruben Quadros on 25/12/21
 **/
sealed class NavigationItem(@StringRes val title: Int, @DrawableRes val icon: Int, val route: String) {

    companion object {
        const val HOME_ROUTE = "landing/home"
        const val BROWSE_ROUTE = "landing/allCompetitions"
        const val LIVE_ROUTE = "landing/live"
        const val PROFILE_ROUTE = "landing/profile"
    }

    object Home: NavigationItem(title = R.string.bottom_navigation_title_home, icon = R.drawable.ic_home, HOME_ROUTE)
    object Browse: NavigationItem(title = R.string.bottom_navigation_title_browse, icon = R.drawable.ic_events, BROWSE_ROUTE)
    object Live: NavigationItem(title = R.string.bottom_navigation_title_live, icon = R.drawable.ic_live, LIVE_ROUTE)
    object Profile: NavigationItem(title = R.string.bottom_navigation_title_profile, icon = R.drawable.ic_profile, PROFILE_ROUTE)
}

