package com.ruben.footiescore.core.domain.mapper

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * Created by Ruben Quadros on 26/12/21
 **/
fun String.toDate(): String {
    val localDateTime = this.toInstant().toLocalDateTime(TimeZone.UTC)
    return "${localDateTime.dayOfMonth}-${localDateTime.monthNumber}-${localDateTime.year}"
}