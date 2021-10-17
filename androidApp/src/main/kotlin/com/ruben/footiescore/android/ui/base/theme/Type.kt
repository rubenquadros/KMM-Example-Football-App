package com.ruben.footiescore.android.ui.base.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ruben.footiescore.android.R

/**
 * Created by Ruben Quadros on 16/10/21
 **/
val Lato = FontFamily(
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_bold, FontWeight.Bold)
)

@Immutable
data class FootieScoreTypography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle
)

val LocalFootieScoreTypography = staticCompositionLocalOf {
    FootieScoreTypography(
        title1 = TextStyle.Default,
        title2 = TextStyle.Default,
        title3 = TextStyle.Default,
        subtitle1 = TextStyle.Default,
        subtitle2 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default,
        button = TextStyle.Default,
        caption = TextStyle.Default,
        overline = TextStyle.Default
    )
}