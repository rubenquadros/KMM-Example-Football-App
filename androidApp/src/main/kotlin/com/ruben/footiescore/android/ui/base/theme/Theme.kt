package com.ruben.footiescore.android.ui.base.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.decode.SvgDecoder

/**
 * Created by Ruben Quadros on 16/10/21
 **/
@Composable
fun FootieScoreTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current

    val localFootieScoreColors = FootieScoreColors(
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

    val localFootieScoreShapes = FootieScoreShapes(
        smallRoundCornerShape = RoundedCornerShape(4.dp),
        mediumRoundCornerShape = RoundedCornerShape(6.dp),
        largeRoundCornerShape = RoundedCornerShape(8.dp)
    )

    val localFootieScoreTypography = FootieScoreTypography(
        title1 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = Lato,
            fontSize = 24.sp,
            lineHeight = 28.8.sp
        ),
        title2 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = Lato,
            fontSize = 22.sp,
            lineHeight = 26.4.sp
        ),
        title3 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = Lato,
            fontSize = 20.sp,
            lineHeight = 24.sp
        ),

        subtitle1 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = Lato,
            fontSize = 18.sp,
            lineHeight = 21.6.sp
        ),

        subtitle2 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = Lato,
            fontSize = 16.sp,
            lineHeight = 19.2.sp
        ),

        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = Lato,
            fontSize = 16.sp,
            lineHeight = 19.2.sp
        ),

        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = Lato,
            fontSize = 14.sp,
            lineHeight = 16.8.sp
        ),

        button = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = Lato,
            fontSize = 18.sp,
            lineHeight = 21.6.sp
        ),

        caption = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = Lato,
            fontSize = 12.sp,
            lineHeight = 14.4.sp
        ),

        overline = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = Lato,
            fontSize = 11.sp,
            lineHeight = 13.2.sp
        )
    )

    val localImageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()

    CompositionLocalProvider(
        LocalFootieScoreColors provides localFootieScoreColors,
        LocalFootieScoreShapes provides localFootieScoreShapes,
        LocalFootieScoreTypography provides localFootieScoreTypography,
        LocalImageLoader provides localImageLoader
    ) {
        MaterialTheme(content = content)
    }
}

object FootieScoreTheme {
    val colors: FootieScoreColors
    @Composable
    @ReadOnlyComposable
    get() = LocalFootieScoreColors.current

    val shapes: FootieScoreShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalFootieScoreShapes.current

    val typography: FootieScoreTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalFootieScoreTypography.current
}