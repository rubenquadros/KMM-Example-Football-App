package com.ruben.footiescore.android.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 30/10/21
 **/
@Composable
fun WelcomeScreen() {
    Box(modifier = Modifier.fillMaxSize().background(FootieScoreTheme.colors.primary))
}