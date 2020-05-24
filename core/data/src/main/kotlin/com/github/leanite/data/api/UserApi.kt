package com.github.leanite.data.api

import com.github.leanite.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): User
}