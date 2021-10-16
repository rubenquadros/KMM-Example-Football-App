package com.ruben.footiescore.android.ui.base

import androidx.compose.material.lightColors
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

val ColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryVariant,
    secondary = SecondaryColor,
    secondaryVariant = SecondaryVariant,
    onPrimary = Color.White,
    onSecondary = Color.White,
    error = ErrorColor,
    onError = Color.White,
    surface = Color.Black,
    onSurface = OnSurface,
    background = BackgroundColor,
    onBackground = OnBackground
)