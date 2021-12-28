package com.ruben.footiescore.android.ui.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.base.theme.Gray100
import com.ruben.footiescore.core.domain.entity.TeamEntity

/**
 * Created by Ruben Quadros on 28/12/21
 **/
@Composable
fun TeamDetailsContent(teamEntity: TeamEntity?) {

    Column(modifier = Modifier.fillMaxWidth()) {
        if (teamEntity != null) {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = FootieScoreTheme.shapes.largeRoundCornerShape,
                backgroundColor = Gray100,
                elevation = 16.dp
            ) {
                TeamDetails(teamEntity)
            }
        } else {
            Button(
                modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally),
                onClick = { },
                shape = FootieScoreTheme.shapes.largeRoundCornerShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = FootieScoreTheme.colors.secondary,
                    contentColor = FootieScoreTheme.colors.onPrimary
                )
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.profile_add_team),
                    style = FootieScoreTheme.typography.button
                )
            }
        }
    }
}

@Composable
fun TeamDetails(teamEntity: TeamEntity) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = ConstraintSet {
            val title = createRefFor("title")
            val teamCrest = createRefFor("team_crest")
            val teamName = createRefFor("team_name")
            val editButton = createRefFor("edit_button")

            constrain(title) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }

            constrain(teamCrest) {
                top.linkTo(title.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            constrain(teamName) {
                top.linkTo(teamCrest.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }

            constrain(editButton) {
                top.linkTo(teamName.top)
                bottom.linkTo(teamName.bottom)
                start.linkTo(teamName.end, margin = 8.dp)
            }
        }
    ) {
        Text(
            modifier = Modifier.layoutId("title"),
            text = stringResource(id = R.string.profile_team_details),
            style = FootieScoreTheme.typography.subtitle1,
            color = FootieScoreTheme.colors.surface
        )

        Image(
            modifier = Modifier.layoutId("team_crest"),
            contentScale = ContentScale.Crop,
            painter = rememberImagePainter(
                data = teamEntity.crestUrl,
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.ic_football_black)
                }
            ),
            contentDescription = stringResource(id = R.string.content_description_team_crest)
        )

        Text(
            modifier = Modifier.layoutId("team_name"),
            text = teamEntity.name,
            style = FootieScoreTheme.typography.subtitle2,
            color = FootieScoreTheme.colors.surface
        )

        IconButton(
            modifier = Modifier.layoutId("edit_button"),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = stringResource(id = R.string.content_description_edit),
                tint = FootieScoreTheme.colors.secondary
            )
        }
    }
}

@Preview
@Composable
fun PreviewTeamDetailsContent(@PreviewParameter(TeamDetailsEntityProvider::class) teamEntity: TeamEntity?) {
    TeamDetailsContent(teamEntity = teamEntity)
}

class TeamDetailsEntityProvider : PreviewParameterProvider<TeamEntity?> {
    override val values: Sequence<TeamEntity?> =
        sequenceOf(
            null,
            TeamEntity(
                id = 66,
                name = "Manchester United FC",
                crestUrl = "",
                shortName = "MUN"
            )
        )

}