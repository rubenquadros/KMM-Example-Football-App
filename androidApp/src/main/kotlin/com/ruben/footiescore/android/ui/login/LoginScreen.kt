package com.ruben.footiescore.android.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme

/**
 * Created by Ruben Quadros on 31/10/21
 **/
@Composable
fun LoginScreen() {
    Box(modifier = Modifier.fillMaxSize().background(FootieScoreTheme.colors.secondaryVariant))
}