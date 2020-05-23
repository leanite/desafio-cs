package com.github.leanite.data.interaction

import com.github.leanite.model.User
import leanite.github.com.github.data.service.UserApi

class GetUserDetailsInteractor(private val api: UserApi) {
    suspend fun execute(username: String): User = api.getUserDetails(username)
}