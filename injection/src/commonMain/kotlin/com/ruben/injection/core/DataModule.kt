package com.ruben.injection.core

import com.ruben.footiescore.core.data.DataSource
import com.ruben.footiescore.core.data.DataSourceImpl
import com.ruben.footiescore.core.data.remote.ApiSource
import com.ruben.footiescore.core.data.remote.ApiSourceImpl
import com.ruben.footiescore.core.data.remote.rest.RestApi
import com.ruben.footiescore.core.data.remote.rest.RestApiImpl
import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.data.repository.FootballRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 11/12/21
 **/
internal val coreDataModule = module {
    single<DataSource> {
        DataSourceImpl(
            get(),
            get(),
            get()
        )
    }
}

internal val coreRepositoryModule = module {
    single<FootballRepository> {
        FootballRepositoryImpl(get(), get())
    }
}

internal val coreRemoteModule =  module {

    single<ApiSource> { ApiSourceImpl(get()) }

    single<RestApi> { RestApiImpl(get()) }
}