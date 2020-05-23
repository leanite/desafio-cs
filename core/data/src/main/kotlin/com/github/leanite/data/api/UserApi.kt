package leanite.github.com.github.data.service

import com.github.leanite.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): User
}