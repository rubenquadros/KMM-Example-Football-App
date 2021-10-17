package com.ruben.footiescore.android.ui.base.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

/**
 * Created by Ruben Quadros on 16/10/21
 **/
@Immutable
data class FootieScoreShapes(
    val smallRoundCornerShape: Shape,
    val mediumRoundCornerShape: Shape,
    val largeRoundCornerShape: Shape
)

val LocalFootieScoreShapes = staticCompositionLocalOf {
    FootieScoreShapes(
        smallRoundCornerShape = RoundedCornerShape(ZeroCornerSize),
        mediumRoundCornerShape = RoundedCornerShape(ZeroCornerSize),
        largeRoundCornerShape = RoundedCornerShape(ZeroCornerSize)
    )
}