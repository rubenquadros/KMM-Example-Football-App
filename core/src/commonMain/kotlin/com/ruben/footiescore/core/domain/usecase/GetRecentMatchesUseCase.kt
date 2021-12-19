package com.ruben.footiescore.core.domain.usecase

import com.ruben.footiescore.core.data.remote.model.response.RecentMatchesResponse
import com.ruben.footiescore.core.data.repository.FootballRepository
import com.ruben.footiescore.core.domain.entity.*
import com.ruben.footiescore.shared.domain.entity.BaseEntity
import com.ruben.footiescore.shared.domain.entity.mapErrorEntity
import com.ruben.footiescore.shared.domain.usecase.BaseUseCase
import com.ruben.footiescore.shared.remote.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ruben Quadros on 19/12/21
 **/
class GetRecentMatchesUseCase(private val repository: FootballRepository): BaseUseCase<Unit, List<RecentMatchesEntity>, Nothing>() {
    override suspend fun execute(request: Unit): Flow<BaseEntity<List<RecentMatchesEntity>, Nothing>> = flow {
        emit(BaseEntity.Loading)
        when (val result = repository.getRecentMatches()) {
            is ApiResponse.Success -> {
                emit(BaseEntity.Success(result.body.toUIEntity()))
            }
            is ApiResponse.ErrorNoBody -> {
                emit(mapErrorEntity(result.code))
            }
            else -> {
                emit(BaseEntity.UnknownError)
            }
        }
    }

}

internal fun RecentMatchesResponse.toUIEntity(): List<RecentMatchesEntity> {
    fun RecentMatchesResponse.Matches.Competition.Area.toUIEntity(): AreaEntity =
        AreaEntity(
            name = this.name,
            code = this.code,
            areaUrl = this.ensignUrl.orEmpty()
        )

    fun RecentMatchesResponse.Matches.Competition.toUIEntity(): CompetitionEntity =
        CompetitionEntity(
            id = this.id,
            name = this.name,
            area = this.area.toUIEntity()
        )

    fun RecentMatchesResponse.Matches.AwayTeam.toUIEntity(): TeamEntity =
        TeamEntity(
            id = this.id,
            name = this.name,
            shortName = this.shortName,
            crestUrl = this.crestUrl.orEmpty()
        )

    fun RecentMatchesResponse.Matches.HomeTeam.toUIEntity(): TeamEntity =
        TeamEntity(
            id = this.id,
            name = this.name,
            shortName = this.shortName,
            crestUrl = this.crestUrl.orEmpty()
        )

    fun RecentMatchesResponse.Matches.Score.toUIEntity(): ScoreEntity =
        ScoreEntity(
            homeTeam = this.fullTime.homeTeam,
            awayTeam = this.fullTime.awayTeam
        )

    return this.matches.map {
        RecentMatchesEntity(
            id = it.id,
            date = it.utcDate,
            competitionEntity = it.competition.toUIEntity(),
            homeTeam = it.homeTeam.toUIEntity(),
            awayTeam = it.awayTeam.toUIEntity(),
            scoreEntity = it.score.toUIEntity()
        )
    }
}