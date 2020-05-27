package com.github.leanite.data.api

import com.github.leanite.data.interaction.GetRepositoriesResponse
import com.github.leanite.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryApi {
    @GET("search/repositories?q=language:Kotlin&sort=stars") //TODO:parametrizar linguagem
    suspend fun getRepositoriesAtPageNumber(
        @Query("page") pageNumber: Int): GetRepositoriesResponse

    @GET("users/{username}/repos?")
    suspend fun getUserRepositoriesAtPageNumber(
        @Path("username") username: String,
        @Query("page") pageNumber: Int): List<Repository>
}