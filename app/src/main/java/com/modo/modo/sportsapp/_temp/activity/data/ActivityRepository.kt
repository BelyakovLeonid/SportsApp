package com.modo.modo.sportsapp._temp.activity.data

import android.util.Log
import com.modo.modo.sportsapp._temp.activity.data.model.Activity
import com.modo.modo.sportsapp._temp.activity.data.remote.ActivityApi
import com.modo.modo.sportsapp._temp.activity.data.remote.model.toDomain

class ActivityRepository(
    private val api: ActivityApi
) {

    private var cachedActivities = emptyList<Activity>()

    /**
     * Получить все доступные активности
     */
    suspend fun getActivities(): List<Activity> {
        val response = api.getActivities()
        Log.d(TAG, "getActivities: response = $response")
        return response.map { it.toDomain() }.also { cachedActivities = it }
    }

    /**
     * Получить активности пользователя по id
     */
    suspend fun getUserActivities(userId: String): List<Activity> {
        val response = api.getUserActivities(userId)
        Log.d(TAG, "getUserActivities: response = $response")
        return response.map { it.toDomain() }
    }

    companion object {
        private const val TAG = "ActivityRepository"
    }
}