package com.ruben.footiescore.android.ui.common

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

/**
 * Created by Ruben Quadros on 12/12/21
 **/
@Composable
fun EmptyBackHandler() {
    BackHandler {
        //cannot go back from here
    }
}