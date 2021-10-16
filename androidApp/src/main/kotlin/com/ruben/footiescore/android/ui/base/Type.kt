package com.ruben.footiescore.android.ui.base

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ruben.footiescore.android.R

/**
 * Created by Ruben Quadros on 16/10/21
 **/
val Lato = FontFamily(
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_bold, FontWeight.Bold)
)

val Typography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Lato,
        fontSize = 24.sp,
        lineHeight = 28.8.sp
    ),

    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Lato,
        fontSize = 22.sp,
        lineHeight = 26.4.sp
    ),

    h3 = TextStyle(
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