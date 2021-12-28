package com.ruben.footiescore.android.ui.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.base.theme.Gray100
import com.ruben.footiescore.core.domain.entity.UserEntity

/**
 * Created by Ruben Quadros on 28/12/21
 **/
@Composable
fun PersonalDetailsContent(userEntity: UserEntity) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = FootieScoreTheme.shapes.largeRoundCornerShape,
            backgroundColor = Gray100,
            elevation = 16.dp
        ) {
            PersonalDetails(userEntity)
        }
    }

}

@Composable
fun PersonalDetails(userEntity: UserEntity) {

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = ConstraintSet {
            val title = createRefFor("title")
            val profilePic = createRefFor("profile_pic")
            val userDetails = createRefFor("user_details")

            constrain(title) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }

            constrain(profilePic) {
                top.linkTo(title.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            constrain(userDetails) {
                top.linkTo(profilePic.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        }
    ) {
        Text(
            modifier = Modifier.layoutId("title"),
            text = stringResource(id = R.string.profile_personal_details),
            style = FootieScoreTheme.typography.subtitle1,
            color = FootieScoreTheme.colors.surface
        )

        Image(
            modifier = Modifier.layoutId("profile_pic"),
            contentScale = ContentScale.Crop,
            painter = rememberImagePainter(
                data = userEntity.profilePic,
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.ic_profile)
                }
            ),
            contentDescription = stringResource(id = R.string.content_description_profile)
        )

        Column(
            modifier = Modifier.layoutId("user_details")
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = userEntity.name,
                style = FootieScoreTheme.typography.subtitle2,
                color = FootieScoreTheme.colors.surface
            )

            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                text = userEntity.email,
                style = FootieScoreTheme.typography.subtitle2,
                color = FootieScoreTheme.colors.surface
            )
        }
    }

}

@Preview
@Composable
fun PreviewPersonalDetailsContent() {
    PersonalDetailsContent(
        userEntity = UserEntity(
            id = "",
            name = "Ruben Quadros",
            email = "rquadros95@gmail.com",
            profilePic = "",
            teamId = null
        )
    )
}