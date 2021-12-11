package com.ruben.injection.shared

import com.ruben.footiescore.shared.domain.dispatcher.DispatcherProvider
import com.ruben.footiescore.shared.domain.dispatcher.DispatcherProviderImpl
import com.ruben.footiescore.shared.remote.ktor.KtorService
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 11/12/21
 **/
internal val sharedModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }

    single { KtorService() }
}