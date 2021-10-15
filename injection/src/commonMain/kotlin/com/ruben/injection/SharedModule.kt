package com.ruben.injection

import com.ruben.footiescore.dispatcher.DispatcherProvider
import com.ruben.footiescore.dispatcher.DispatcherProviderImpl
import com.ruben.footiescore.interactor.GetAllCompetitionsUseCase
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 15/10/21
 **/
val useCaseModule = module {
    factory {
        GetAllCompetitionsUseCase(get())
    }
}

val sharedModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}