package com.ruben.footiescore.android.ui.common

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/**
 * Created by Ruben Quadros on 30/10/21
 **/
class WelcomeScreenShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawBottomWiggle(size = size)
        )
    }
}

fun drawBottomWiggle(size: Size) : Path {
    return Path().apply {
        reset()
        lineTo(0f, size.height)
        cubicTo(
            (0.45 * size.width).toFloat(),
            (0.65 * size.height).toFloat(),
            size.width / 2,
            (1.5 * size.height).toFloat(),
            size.width,
            size.height
        )
        lineTo(size.width, 0f)
        lineTo(0f, 0f)
        close()
    }
}