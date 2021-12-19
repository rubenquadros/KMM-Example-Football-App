package com.ruben.footiescore.android.ui.home.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.slideInHorizontallyAnim
import com.ruben.footiescore.android.ui.common.slideOutHorizontallyAnim

/**
 * Created by Ruben Quadros on 28/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.BrowseContent(
    modifier: Modifier = Modifier,
    isTeamNotSelected: Boolean
) {
    val density = LocalDensity.current

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .animateEnterExit(
                enter = slideInHorizontallyAnim(
                    offset = with(density) { -100.dp.roundToPx() },
                    duration = 800
                ) + fadeIn(),
                exit = slideOutHorizontallyAnim(
                    offset = with(density) { 100.dp.roundToPx() },
                    duration = 400
                ) + fadeOut()
            ),
        constraintSet = ConstraintSet {
            val title = createRefFor("title")
            val content = createRefFor("content")

            constrain(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(content) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        }
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .layoutId("title"),
            text = stringResource(id = R.string.home_dashboard_browse_content),
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .layoutId("content"),
            elevation = 10.dp,
            shape = FootieScoreTheme.shapes.largeRoundCornerShape,
            backgroundColor = FootieScoreTheme.colors.primaryVariant
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (isTeamNotSelected) {
                    ContentItem(
                        image = R.drawable.ic_favorite,
                        title = R.string.home_dashboard_browse_select_team_title,
                        description = R.string.home_dashboard_browse_select_team_desc
                    )
                }

                ContentItem(
                    image = R.drawable.ic_sports,
                    title = R.string.home_dashboard_browse_live_matches_title,
                    description = R.string.home_dashboard_browse_live_matches_desc
                )

                ContentItem(
                    image = R.drawable.ic_events,
                    title = R.string.home_dashboard_browse_competitions_title,
                    description = R.string.home_dashboard_browse_competitions_desc,
                    shouldShowDivider = false
                )
            }
        }
    }
}

@Composable
fun ContentItem(
    @DrawableRes image: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    shouldShowDivider: Boolean = true
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = ConstraintSet {
            val logo = createRefFor("logo")
            val heading = createRefFor("heading")
            val desc = createRefFor("desc")
            val divider = createRefFor("divider")

            constrain(logo) {
                top.linkTo(heading.top)
                start.linkTo(parent.start, margin = 16.dp)
                bottom.linkTo(desc.bottom)
                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }

            constrain(heading) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(logo.end, margin = 8.dp)
                end.linkTo(parent.end, margin = 8.dp)
                width = Dimension.fillToConstraints
            }

            constrain(desc) {
                top.linkTo(heading.bottom, margin = 8.dp)
                start.linkTo(logo.end, margin = 8.dp)
                end.linkTo(parent.end, margin = 8.dp)
                width = Dimension.fillToConstraints
            }

            constrain(divider) {
                top.linkTo(desc.bottom, margin = 4.dp)
                start.linkTo(parent.start, margin = 18.dp)
                end.linkTo(parent.end, margin = 18.dp)
                height = Dimension.value(1.dp)
                width = Dimension.fillToConstraints
            }
        }
    ) {

        Image(
            modifier = Modifier.layoutId("logo"),
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.content_description_favourite)
        )

        Text(
            modifier = Modifier.layoutId("heading"),
            text = stringResource(id = title),
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.onPrimary
        )

        Text(
            modifier = Modifier.layoutId("desc"),
            text = stringResource(id = description),
            style = FootieScoreTheme.typography.body1,
            color = FootieScoreTheme.colors.onPrimary
        )

        if (shouldShowDivider) {
            Box(
                modifier = Modifier
                    .background(color = FootieScoreTheme.colors.onBackground)
                    .layoutId("divider")
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewBrowseContent() {
    AnimatedVisibility(visible = true) {
        BrowseContent(isTeamNotSelected = true)
    }
}

@Preview
@Composable
fun PreviewContentItem() {
    ContentItem(
        image = R.drawable.ic_sports,
        title = R.string.home_dashboard_browse_live_matches_title,
        description = R.string.home_dashboard_browse_live_matches_desc
    )
}
