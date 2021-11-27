package com.ruben.footiescore.android.di

import com.ruben.footiescore.android.ui.activity.MainViewModel
import com.ruben.footiescore.android.ui.favteam.SelectFavTeamViewModel
import com.ruben.footiescore.android.ui.home.HomeViewModel
import com.ruben.footiescore.android.ui.login.LoginViewModel
import com.ruben.footiescore.android.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 15/10/21
 **/
val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { WelcomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SelectFavTeamViewModel(get()) }
}