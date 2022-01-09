package com.ruben.footiescore.android.ui.live

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.ruben.footiescore.android.R
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import com.ruben.footiescore.android.ui.common.BallLoader
import com.ruben.footiescore.android.ui.common.ErrorView
import com.ruben.footiescore.android.ui.common.NoLiveMatchesView
import com.ruben.footiescore.android.ui.common.fadeInAnim
import com.ruben.footiescore.android.ui.common.fadeOutAnim
import com.ruben.footiescore.android.ui.common.slideInVerticallyAnim
import com.ruben.footiescore.android.ui.common.slideOutVerticallyAnim
import com.ruben.footiescore.android.ui.live.component.LiveMatchesContent

/**
 * Created by Ruben Quadros on 09/01/22
 **/
@Composable
fun AnimatedVisibilityScope.LiveMatchesScreen(
    liveMatchesViewModel: LiveMatchesViewModel
) {
    val density = LocalDensity.current

    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = liveMatchesViewModel.uiState()
    val stateLifecycleAware = remember(lifecycleOwner, stateFlow) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by stateLifecycleAware.collectAsState(initial = liveMatchesViewModel.createInitialState())

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is LiveMatchesState.MatchesState -> {
                (state as? LiveMatchesState.MatchesState)?.matches?.let {
                    LiveMatchesContent(
                        modifier = Modifier.fillMaxSize(),
                        matches = it
                    )
                }
            }

            is LiveMatchesState.NoLiveMatchesState -> {
                NoLiveMatchesView(
                    enterTransition = slideInVerticallyAnim(
                        offset = with(density) { -100.dp.roundToPx() },
                        duration = 600
                    ) + fadeInAnim(),
                    exitTransition = slideOutVerticallyAnim(
                        offset = with(density) { 100.dp.roundToPx() },
                        duration = 300
                    ) + fadeOutAnim(),
                    message = stringResource(id = R.string.live_matches_not_live)
                )
            }

            is LiveMatchesState.ErrorState -> {
                ErrorView(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.Center),
                    errorMessage = stringResource(id = R.string.live_matches_error),
                    enterTransition = slideInVerticallyAnim(
                        offset = with(density) { -100.dp.roundToPx() },
                        duration = 600
                    ) + fadeInAnim(),
                    exitTransition = slideOutVerticallyAnim(
                        offset = with(density) { 100.dp.roundToPx() },
                        duration = 300
                    ) + fadeOutAnim()
                )
            }
            else -> { /*do nothing*/ }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = state.shouldShowLoading
        ) {
            BallLoader(
                modifier = Modifier.size(40.dp),
                ballColor = FootieScoreTheme.colors.primary
            )
        }
    }
}