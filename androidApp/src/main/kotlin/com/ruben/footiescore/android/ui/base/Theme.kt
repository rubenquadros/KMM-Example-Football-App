package com.ruben.footiescore.android.ui.base

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Created by Ruben Quadros on 16/10/21
 **/
@Composable
fun FootieScoreTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}