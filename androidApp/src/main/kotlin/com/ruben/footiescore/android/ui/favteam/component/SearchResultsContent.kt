package com.ruben.footiescore.android.ui.favteam.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.core.domain.entity.SearchTeamEntity

/**
 * Created by Ruben Quadros on 12/12/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.SearchResults(
    modifier: Modifier = Modifier,
    enterTransition: EnterTransition,
    exitTransition: ExitTransition,
    lazyListState: LazyListState,
    searchResults: List<SearchTeamEntity>,
    onClick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .animateEnterExit(
                enter = enterTransition,
                exit = exitTransition
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyListState
    ) {
        items(items = searchResults, key = { item -> item.id }) { teamEntity ->
            SearchResultItem(
                teamEntity = teamEntity,
                onClick = onClick
            )
        }
    }
}

@Composable
fun SearchResultItem(
    teamEntity: SearchTeamEntity,
    onClick: (id: Int) -> Unit
) {

    Row(modifier = Modifier
        .padding(horizontal = 8.dp)
        .fillMaxWidth()
        .clickable {
            onClick.invoke(teamEntity.id)
        }
    ) {
        Image(
            modifier = Modifier
                .size(60.dp)
                .padding(vertical = 4.dp),
            painter = if (teamEntity.image.isEmpty()) {
                painterResource(id = R.drawable.ic_football_black)
            } else {
                rememberImagePainter(
                    data = teamEntity.image,
                    builder = {
                        placeholder(R.drawable.ic_football_black)
                    }
                )
                                                           },
            contentDescription = stringResource(id = R.string.content_description_team_crest)
        )

        Column(modifier = Modifier
            .padding(start = 16.dp)
            .align(Alignment.CenterVertically)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = teamEntity.name,
                style = FootieScoreTheme.typography.body1,
                color = FootieScoreTheme.colors.surface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                text = teamEntity.area,
                style = FootieScoreTheme.typography.body2,
                color = FootieScoreTheme.colors.disabled
            )
        }
    }

    Divider(
        color = FootieScoreTheme.colors.disabled,
        thickness = 1.dp
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(name = "Search content")
@Composable
fun PreviewSelectTeamScreen() {
    AnimatedVisibility(visible = true) {
        SearchResults(
            modifier = Modifier.background(FootieScoreTheme.colors.onPrimary),
            enterTransition = fadeInAnim(),
            exitTransition = fadeOutAnim(),
            lazyListState = rememberLazyListState(),
            searchResults = listOf(
                SearchTeamEntity(
                    id = 1,
                    name = "Manchester United",
                    area = "England",
                    image = ""
                ),
                SearchTeamEntity(
                    id = 2,
                    name = "Real Madrid",
                    area = "Spain",
                    image = ""
                ),
                SearchTeamEntity(
                    id = 3,
                    name = "Paris Saint Germain",
                    area = "France",
                    image = ""
                ),
                SearchTeamEntity(
                    id = 4,
                    name = "Bayern Munich",
                    area = "Germany",
                    image = ""
                ),
                SearchTeamEntity(
                    id = 5,
                    name = "Juventus",
                    area = "Italy",
                    image = ""
                ),
                SearchTeamEntity(
                    id = 6,
                    name = "Liverpool",
                    area = "England",
                    image = ""
                ),
                SearchTeamEntity(
                    id = 7,
                    name = "Manchester City",
                    area = "England",
                    image = ""
                ),
                SearchTeamEntity(
                    id = 8,
                    name = "Barcelona",
                    area = "Spain",
                    image = ""
                )
            ),
            onClick = {  }
        )
    }
}

@Preview(name = "Search Item")
@Composable
fun PreviewSearchResultItem() {
    SearchResultItem(
        teamEntity = SearchTeamEntity(
            id = 1,
            name = "Manchester United",
            area = "England",
            image = ""
        ),
        onClick = { }
    )
}