package com.modo.modo.sportsapp._temp.activity.data.remote.model

import com.modo.modo.sportsapp._temp.activity.data.model.Activity
import com.modo.modo.sportsapp.base.utils.toLocalDateTime
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class ActivityDto(
    val id: String,
    val activityDate: String? = null,
    val description: String,
    val imageUrl: String,
    val type: String
)

fun ActivityDto.toDomain(): Activity {
    return Activity(
        id = id,
        activityDate = activityDate?.toLocalDateTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        description = description,
        imageUrl = imageUrl,
        type = type
    )
}
