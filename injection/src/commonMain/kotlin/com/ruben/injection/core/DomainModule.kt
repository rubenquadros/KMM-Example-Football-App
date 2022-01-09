package com.ruben.injection.core

import com.ruben.footiescore.core.domain.usecase.*
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

    factory { GetRecentMatchesUseCase(get()) }

    factory { GetUserDataUseCase(get()) }

    factory { GetUserProfileUseCase(get()) }

    factory { GetLiveMatchesUseCase(get()) }
}