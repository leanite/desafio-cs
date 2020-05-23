package com.github.leanite.data.api

import com.github.leanite.data.interaction.GetAmountPullRequestsResponse
import com.github.leanite.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullRequestApi {
    @GET("repos/{username}/{repositoryName}/pulls?")
    fun getPullRequests(
        @Path("username") username: String,
        @Path("repositoryName") repositoryName: String,
        @Query("state") state: String,
        @Query("page") pageNumber: Int
    ): List<PullRequest>

    @GET("search/issues?")
    fun getAmountPullRequests(
        @Query(value = "q", encoded = true) query: String
    ): GetAmountPullRequestsResponse
}