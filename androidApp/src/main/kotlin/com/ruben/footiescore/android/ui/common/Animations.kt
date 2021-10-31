package com.ruben.footiescore.android.ui.common

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

/**
 * Created by Ruben Quadros on 31/10/21
 **/

/**
 * @param offset - initialOffsetY
 * @param duration - durationInMillis
 */
@OptIn(ExperimentalAnimationApi::class)
fun slideInVerticallyAnim(offset: Int = 0, duration: Int = 0) : EnterTransition {
    return slideInVertically(
        initialOffsetY = { offset },
        animationSpec = tween(durationMillis = duration)
    )
}

/**
 * @param offset - targetOffsetY
 * @param duration - durationInMillis
 */
@OptIn(ExperimentalAnimationApi::class)
fun slideOutVerticallyAnim(offset: Int = 0, duration: Int = 0) : ExitTransition {
    return slideOutVertically(
        targetOffsetY = { offset },
        animationSpec = tween(durationMillis = duration)
    )
}

/**
 * @param alpha - initialAlpha
 * @param duration - durationInMillis
 */
@OptIn(ExperimentalAnimationApi::class)
fun fadeInAnim(alpha: Float = 0f, duration: Int = 0) : EnterTransition {
    return fadeIn(
        initialAlpha = alpha,
        animationSpec = tween(durationMillis = duration)
    )
}

/**
 * @param alpha - targetAlpha
 * @param duration - durationInMillis
 */
@OptIn(ExperimentalAnimationApi::class)
fun fadeOutAnim(alpha: Float = 0f, duration: Int = 0) : ExitTransition {
    return fadeOut(
        targetAlpha = alpha,
        animationSpec = tween(durationMillis = duration)
    )
}