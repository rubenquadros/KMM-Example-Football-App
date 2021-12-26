package com.ruben.footiescore.android.ui.base.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Created by Ruben Quadros on 16/10/21
 **/
val PrimaryColor = Color(0xFF478925)
val PrimaryVariant = Color(0xFF78b953)
val SecondaryColor = Color(0xFFfc6d67)
val SecondaryVariant = Color(0xFFff9f95)
val ErrorColor = Color(0xFFc43b3c)
val OnSurface = Color(0xFF607D8B)
val BackgroundColor = Color(0xFF105b00)
val OnBackground = Color(0xFFf8f277)
val DisabledColor = Color(0xFFC4C4C4)
val Gray100 = Color(0xFFF5F5F5)

@Immutable
data class FootieScoreColors(
    val primary: Color,
    val secondary: Color,
    val primaryVariant: Color,
    val secondaryVariant: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val error: Color,
    val onPrimary: Color,
    val disabled: Color
)

val LocalFootieScoreColors = staticCompositionLocalOf {
    FootieScoreColors(
        primary = PrimaryColor,
        secondary = SecondaryColor,
        primaryVariant = PrimaryVariant,
        secondaryVariant = SecondaryVariant,
        background = BackgroundColor,
        onBackground = OnBackground,
        surface = Color.Black,
        onSurface = OnSurface,
        error = ErrorColor,
        onPrimary = Color.White,
        disabled = DisabledColor
    )
}