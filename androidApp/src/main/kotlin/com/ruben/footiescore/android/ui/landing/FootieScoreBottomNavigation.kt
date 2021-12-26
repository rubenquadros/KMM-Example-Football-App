package com.ruben.footiescore.android.ui.landing

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 25/12/21
 **/
@Composable
fun FootieScoreBottomNavigation(
    items: List<NavigationItem>,
    onClick: (route: String) -> Unit,
    getCurrentScreen: () -> String
) {
    BottomNavigation(
        backgroundColor = FootieScoreTheme.colors.primary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = item.route == getCurrentScreen.invoke(),
                onClick = { onClick.invoke(item.route) },
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = "") },
                label = { Text(text = stringResource(id = item.title)) },
                alwaysShowLabel = false,
                selectedContentColor = FootieScoreTheme.colors.secondaryVariant,
                unselectedContentColor = FootieScoreTheme.colors.disabled
            )
        }
    }
}

@Preview
@Composable
fun PreviewFootieScoreBottomNavigation() {
    FootieScoreBottomNavigation(
        items = listOf(
            NavigationItem.Home,
            NavigationItem.Browse,
            NavigationItem.Live,
            NavigationItem.Profile
        ),
        onClick = {},
        getCurrentScreen = { "landing/home" }
    )
}