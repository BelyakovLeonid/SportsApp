package com.modo.modo.sportsapp.base.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

const val FULL_DATE_TIME = "yyyy-MM-dd HH:mm"

fun String.toLocalDateTime(formatter: DateTimeFormatter): LocalDateTime {
    return LocalDateTime.parse(this, formatter)
}

fun LocalDateTime.formatToString(): String {
    val formatter = DateTimeFormatter.ofPattern(FULL_DATE_TIME)
    return this.format(formatter)
}

fun LocalDateTime.isNearFromNow(): Boolean {
    val now = LocalDateTime.now()
    return ChronoUnit.HOURS.between(this, now) < 2
}