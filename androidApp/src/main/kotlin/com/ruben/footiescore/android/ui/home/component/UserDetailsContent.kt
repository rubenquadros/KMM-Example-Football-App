package com.ruben.footiescore.android.ui.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.insets.cutoutPadding
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.BottomWiggleShape
import com.ruben.footiescore.android.ui.common.slideInHorizontallyAnim
import com.ruben.footiescore.android.ui.common.slideOutHorizontallyAnim
import com.ruben.footiescore.core.domain.entity.UserEntity

/**
 * Created by Ruben Quadros on 28/11/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScope.UserDetailsContent(
    isUserLoggedIn: Boolean,
    userDetails: UserEntity?
) {
    val density = LocalDensity.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .cutoutPadding()
            .animateEnterExit(
                enter = slideInHorizontallyAnim(
                    offset = with(density) { -100.dp.roundToPx() },
                    duration = 800
                ) + fadeIn(),
                exit = slideOutHorizontallyAnim(
                    offset = with(density) { 100.dp.roundToPx() },
                    duration = 400
                ) + fadeOut()
            )
            .background(color = FootieScoreTheme.colors.primary, shape = BottomWiggleShape()),
        constraintSet = ConstraintSet {
            val details = createRefFor("user_details")

            constrain(details) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        }
    ) {
        Column(modifier = Modifier.layoutId("user_details")) {
            Text(
                text = stringResource(id = R.string.home_dashboard_name, userDetails?.name.orEmpty()),
                style = FootieScoreTheme.typography.body1,
                color = FootieScoreTheme.colors.onPrimary
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = if (isUserLoggedIn) stringResource(id = R.string.home_dashboard_welcome)
                else stringResource(id = R.string.all_welcome),
                style = FootieScoreTheme.typography.title3,
                color = FootieScoreTheme.colors.onPrimary
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