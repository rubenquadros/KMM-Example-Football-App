package com.ruben.footiescore.android.di

import com.ruben.footiescore.android.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 15/10/21
 **/
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}