package com.modo.modo.sportsapp._temp.user.data

import android.util.Log
import com.modo.modo.sportsapp._temp.user.data.remote.UserApi
import com.modo.modo.sportsapp._temp.user.data.remote.model.EventRegistration
import com.modo.modo.sportsapp._temp.user.data.remote.model.User

class UserRepository(
    private val api: UserApi
) {

    /**
     * Получить всех пользователей
     */
    suspend fun getAllUsers(): List<User> {
        val response = api.getAllUsers()
        Log.d(TAG, "getAllUsers: response = $response")
        return response
    }

    /**
     * Получить пользователя по ID
     */
    suspend fun getUserById(id: String): User {
        val response = api.getUserById(id)
        Log.d(TAG, "getUserById: response = $response")
        return response
    }

    /**
     * Зарегистрировать пользователя на мероприятие
     */
    suspend fun registerUserToEvent(eventRegistration: EventRegistration): EventRegistration {
        val response = api.registerUserToEvent(eventRegistration)
        Log.d(TAG, "registerUserToEvent: response = $response")
        return response
    }

    /**
     * Получить все мероприятия пользователя по ID
     */
    suspend fun getUserEventsById(id: String): List<EventRegistration> {
        val response = api.getUserEventsById(id)
        Log.d(TAG, "getUserEventsById: response = $response")
        return response
    }

    companion object {
        private const val TAG = "UserRepository"
    }
}