package com.ruben.footiescore.android.ui.home.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.ruben.footiescore.android.ui.base.theme.Gray100
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInHorizontallyAnim
import com.ruben.footiescore.android.ui.common.slideOutHorizontallyAnim
import com.ruben.footiescore.core.domain.entity.AreaEntity
import com.ruben.footiescore.core.domain.entity.CompetitionEntity

/**
 * Created by Ruben Quadros on 28/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.FeedContent(
    modifier: Modifier = Modifier,
    isNotLoggedIn: Boolean,
    isTeamNotSelected: Boolean,
    competition: CompetitionEntity
) {
    val density = LocalDensity.current

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .animateEnterExit(
                enter = slideInHorizontallyAnim(
                    offset = with(density) { -100.dp.roundToPx() },
                    duration = 800
                ) + fadeInAnim(),
                exit = slideOutHorizontallyAnim(
                    offset = with(density) { 100.dp.roundToPx() },
                    duration = 400
                ) + fadeOutAnim()
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
            text = stringResource(id = R.string.home_dashboard_feed),
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .layoutId("content"),
            elevation = 16.dp,
            shape = FootieScoreTheme.shapes.largeRoundCornerShape,
            backgroundColor = Gray100
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (isNotLoggedIn) {
                    ContentItem(
                        image = R.drawable.ic_login,
                        title = stringResource(id = R.string.home_dashboard_feed_login_title),
                        description = stringResource(id = R.string.home_dashboard_feed_login_desc)
                    )
                } else if (isTeamNotSelected) {
                    ContentItem(
                        image = R.drawable.ic_favorite,
                        title = stringResource(id = R.string.home_dashboard_feed_select_team_title),
                        description = stringResource(id = R.string.home_dashboard_feed_select_team_desc)
                    )
                }

                ContentItem(
                    image = R.drawable.ic_sports,
                    title = competition.name,
                    description = stringResource(id = R.string.home_dashboard_feed_competition_desc)
                )

                ContentItem(
                    image = R.drawable.ic_sports,
                    title = stringResource(id = R.string.home_dashboard_browse_competitions_title),
                    description = stringResource(id = R.string.home_dashboard_browse_competitions_desc),
                    shouldShowDivider = false
                )
            }
        }
    }
}

@Composable
fun ContentItem(
    @DrawableRes image: Int,
    title: String,
    description: String,
    shouldShowDivider: Boolean = true
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = ConstraintSet {
            val logo = createRefFor("logo")
            val heading = createRefFor("heading")
            val desc = createRefFor("desc")
            val chevron = createRefFor("chevron")
            val divider = createRefFor("divider")

            constrain(logo) {
                top.linkTo(heading.top)
                start.linkTo(parent.start, margin = 16.dp)
                bottom.linkTo(desc.bottom)
                height = Dimension.value(40.dp)
                width = Dimension.value(40.dp)
            }

            constrain(chevron) {
                top.linkTo(heading.top)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(desc.bottom)
                height = Dimension.value(32.dp)
                width = Dimension.value(32.dp)
            }

            constrain(heading) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(logo.end, margin = 8.dp)
                end.linkTo(chevron.start, margin = 8.dp)
                width = Dimension.fillToConstraints
            }

            constrain(desc) {
                top.linkTo(heading.bottom, margin = 8.dp)
                start.linkTo(logo.end, margin = 8.dp)
                end.linkTo(chevron.start, margin = 8.dp)
                width = Dimension.fillToConstraints
            }

            constrain(divider) {
                top.linkTo(desc.bottom, margin = 16.dp)
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
            text = title,
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        Text(
            modifier = Modifier.layoutId("desc"),
            text = description,
            style = FootieScoreTheme.typography.body1,
            color = FootieScoreTheme.colors.surface
        )

        Image(
            modifier = Modifier.layoutId("chevron"),
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = ""
        )

        if (shouldShowDivider) {
            Box(
                modifier = Modifier
                    .background(color = FootieScoreTheme.colors.onSurface)
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
        FeedContent(
            competition = CompetitionEntity(
                id = 1,
                name = "Premier League",
                area = AreaEntity(name = "England", code = "", areaUrl = "")
            ),
            isTeamNotSelected = true,
            isNotLoggedIn = true
        )
    }
}

@Preview
@Composable
fun PreviewContentItem() {
    ContentItem(
        image = R.drawable.ic_sports,
        title = stringResource(id = R.string.home_dashboard_feed_login_title),
        description = stringResource(id = R.string.home_dashboard_feed_login_desc)
    )
}
