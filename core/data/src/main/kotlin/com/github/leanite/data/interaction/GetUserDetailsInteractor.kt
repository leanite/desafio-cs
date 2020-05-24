package com.github.leanite.data.interaction

import com.github.leanite.model.User
import com.github.leanite.data.api.UserApi

class GetUserDetailsInteractor(private val api: UserApi) {
    suspend fun execute(username: String): User = api.getUserDetails(username)
}