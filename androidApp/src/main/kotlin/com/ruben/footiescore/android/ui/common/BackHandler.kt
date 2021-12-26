package com.ruben.footiescore.android.ui.common

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ruben.footiescore.android.R

/**
 * Created by Ruben Quadros on 12/12/21
 **/
@Composable
fun EmptyBackHandler() {
    BackHandler {
        //cannot go back from here
    }
}

@Composable
fun LandingBackHandler() {
    val context = LocalContext.current
    val handler = Handler(Looper.getMainLooper())
    val exitMessage = stringResource(id = R.string.all_back_pressed)
    var isEnabled by remember {
        mutableStateOf(true)
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            handler.removeCallbacksAndMessages(null)
        }
    }

    BackHandler(
        enabled = isEnabled,
        onBack = {
            Toast.makeText(context, exitMessage, Toast.LENGTH_LONG).show()
            isEnabled = false
            handler.postDelayed({
                isEnabled = true
            }, 2000)
        }
    )
}