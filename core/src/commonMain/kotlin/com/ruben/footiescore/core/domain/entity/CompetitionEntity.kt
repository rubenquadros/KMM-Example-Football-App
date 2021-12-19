package com.ruben.footiescore.core.domain.entity

/**
 * Created by Ruben Quadros on 19/12/21
 **/
data class CompetitionEntity(
    val id: Int,
    val name: String,
    val area: AreaEntity
)