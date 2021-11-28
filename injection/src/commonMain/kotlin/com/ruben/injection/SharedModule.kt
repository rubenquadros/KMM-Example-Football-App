package com.ruben.injection

import com.ruben.footiescore.dispatcher.DispatcherProvider
import com.ruben.footiescore.dispatcher.DispatcherProviderImpl
import com.ruben.footiescore.usecase.*
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 15/10/21
 **/
internal val useCaseModule = module {
    factory { GetAllCompetitionsUseCase(get()) }

    factory { GetFirstTimeLaunchUseCase(get()) }

    factory { StoreFirstTimeLaunchUseCase(get()) }

    factory { LoginUseCase(get()) }

    factory { SearchTeamUseCase(get()) }

    factory { GetLoginStateUseCase(get()) }

    factory { SetLoginStateUseCase(get()) }
}

internal val sharedModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}