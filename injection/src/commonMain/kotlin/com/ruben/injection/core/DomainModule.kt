package com.ruben.injection.core

import com.ruben.footiescore.core.domain.usecase.GetAllCompetitionsUseCase
import com.ruben.footiescore.core.domain.usecase.GetFirstTimeLaunchUseCase
import com.ruben.footiescore.core.domain.usecase.GetLoginStateUseCase
import com.ruben.footiescore.core.domain.usecase.LoginUseCase
import com.ruben.footiescore.core.domain.usecase.SaveTeamUseCase
import com.ruben.footiescore.core.domain.usecase.SearchTeamUseCase
import com.ruben.footiescore.core.domain.usecase.SetLoginStateUseCase
import com.ruben.footiescore.core.domain.usecase.StoreFirstTimeLaunchUseCase
import org.koin.dsl.module

/**
 * Created by Ruben Quadros on 11/12/21
 **/
internal val coreUseCaseModule = module {
    factory { GetAllCompetitionsUseCase(get()) }

    factory { GetFirstTimeLaunchUseCase(get()) }

    factory { StoreFirstTimeLaunchUseCase(get()) }

    factory { LoginUseCase(get()) }

    factory { SearchTeamUseCase(get()) }

    factory { GetLoginStateUseCase(get()) }

    factory { SetLoginStateUseCase(get()) }

    factory { SaveTeamUseCase(get()) }
}