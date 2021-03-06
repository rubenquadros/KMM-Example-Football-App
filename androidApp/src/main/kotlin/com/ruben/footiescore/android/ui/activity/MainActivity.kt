package com.ruben.footiescore.android.ui.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.insets.ProvideWindowInsets
import com.ruben.footiescore.android.ui.FootieScoreApp
import com.ruben.footiescore.android.ui.base.theme.FootieScoreTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //check if first time
        viewModel.getIsFirstTimeLaunch()

        //animate splash screen exit
        setSplashAnimation(installSplashScreen())

        //observe state
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState().collect { state ->
                    when (state) {
                        is MainState.InitialState -> { /*do nothing*/ }
                        is MainState.AppValues -> {
                            setContent {
                                FootieScoreTheme {
                                    ProvideWindowInsets {
                                        FootieScoreApp(state.isFirstTime)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setSplashAnimation(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val fadeOut = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.ALPHA,
                1f,
                0f
            )
            fadeOut.interpolator = AnticipateInterpolator()
            fadeOut.duration = 500L

            fadeOut.doOnEnd { splashScreenView.remove() }

            fadeOut.start()
        }
    }
}
