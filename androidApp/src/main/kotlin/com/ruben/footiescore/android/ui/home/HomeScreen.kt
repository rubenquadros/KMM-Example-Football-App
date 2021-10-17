package com.ruben.footiescore.android.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.ruben.footiescore.android.ui.common.PitchLoader
import org.koin.androidx.compose.getViewModel

/**
 * Created by Ruben Quadros on 17/10/21
 **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = getViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = homeViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = homeViewModel.createInitialState())

    var isVisible by remember {
        mutableStateOf(false)
    }

    when (state) {
        is HomeState.InitialState -> {
            isVisible = true
        }
        else -> {
            isVisible = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PitchLoader(
            modifier = Modifier.fillMaxSize(),
            isVisible = isVisible
        )
    }
}