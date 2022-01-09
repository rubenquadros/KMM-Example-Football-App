package com.ruben.footiescore.android.di

import com.ruben.footiescore.android.ui.activity.MainViewModel
import com.ruben.footiescore.android.ui.favteam.SelectFavTeamViewModel
import com.ruben.footiescore.android.ui.competitions.CompetitionsViewModel
import com.ruben.footiescore.android.ui.home.HomeViewModel
import com.ruben.footiescore.android.ui.landing.LandingViewModel
import com.ruben.footiescore.android.ui.live.LiveMatchesViewModel
import com.ruben.footiescore.android.ui.login.LoginViewModel
import com.ruben.footiescore.android.ui.profile.ProfileViewModel
import com.ruben.footiescore.android.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 15/10/21
 **/
val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { CompetitionsViewModel(get()) }
    viewModel { WelcomeViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SelectFavTeamViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { LandingViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { LiveMatchesViewModel(get()) }
}