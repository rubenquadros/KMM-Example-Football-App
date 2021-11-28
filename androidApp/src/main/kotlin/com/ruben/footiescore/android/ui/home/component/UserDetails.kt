package com.ruben.footiescore.android.ui.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.ruben.footiescore.entity.UserEntity

/**
 * Created by Ruben Quadros on 28/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.UserDetailsContent(
    isUserLoggedIn: Boolean,
    userDetails: UserEntity?
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .animateEnterExit(),
        constraintSet = ConstraintSet {
            val details = createRefFor("user_details")
            val profile = createRefFor("profile")

            constrain(profile) {
                top.linkTo(parent.top, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.value(40.dp)
                height = Dimension.value(40.dp)
            }

            constrain(details) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(profile.start, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        }
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .layoutId("profile"),
            contentScale = ContentScale.Crop,
            painter = if (isUserLoggedIn.not() || userDetails?.profilePic.isNullOrBlank()) {
                painterResource(id = R.drawable.ic_profile)
            } else {
                   rememberImagePainter(
                       data = userDetails?.profilePic,
                       builder = {
                           crossfade(true)
                           transformations(CircleCropTransformation())
                       }
                   )
            },
            contentDescription = stringResource(id = R.string.content_description_profile)
        )

        Column(modifier = Modifier.layoutId("user_details")) {
            Text(
                text = stringResource(id = R.string.home_dashboard_name, userDetails?.name.orEmpty()),
                style = FootieScoreTheme.typography.body1,
                color = FootieScoreTheme.colors.onSurface
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = if (isUserLoggedIn) stringResource(id = R.string.home_dashboard_welcome)
                else stringResource(id = R.string.all_welcome),
                style = FootieScoreTheme.typography.title3,
                color = FootieScoreTheme.colors.onSurface
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewUserDetailsContent(
    @PreviewParameter(UserLoggedInStateProvider::class) isUserLoggedIn: Boolean,
) {
    AnimatedVisibility(visible = true) {
        UserDetailsContent(
            isUserLoggedIn = isUserLoggedIn,
            userDetails = UserEntity("123", "Ruben Quadros", "rquadros95@gmail.com", "", -1)
        )
    }
}

class UserLoggedInStateProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(false, true)
}