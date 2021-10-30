package com.ruben.injection

import com.ruben.data.DataSource
import com.ruben.data.DataSourceImpl
import com.ruben.data.FootballRepositoryImpl
import com.ruben.footiescore.FootballRepository
import com.ruben.remote.ApiClient
import com.ruben.remote.rest.RestApi
import com.ruben.remote.rest.RestApiImpl
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 15/10/21
 **/
internal val dataModule = module {
    single<DataSource> { DataSourceImpl(get(), get()) }
}

internal val repositoryModule = module {
    single<FootballRepository> {
        FootballRepositoryImpl(get(), get())
    }
}

internal val remoteModule =  module {
    single<RestApi> { RestApiImpl(get()) }

    single { ApiClient() }
}