package com.github.leanite.data.interaction

import com.github.leanite.data.api.PullRequestApi
import com.google.gson.annotations.SerializedName

class GetAmountPullRequestsInteractor(private val api: PullRequestApi) {

    suspend fun execute(request: GetAmountPullRequestsRequest): GetAmountPullRequestsResponse =
        api.getAmountPullRequests(
            createQuery(request.repositoryUsername, request.repositoryName, request.state))
//            response.state = PullRequest.State.valueOf(request.state.toUpperCase())

    private fun createQuery(
        repositoryUsername: String, repositoryName: String, state: String
    ): String = "+type:pr+repo:$repositoryUsername/$repositoryName+state:$state"
}

data class GetAmountPullRequestsRequest(
    val repositoryUsername: String,
    val repositoryName: String,
    val state: String
)

data class GetAmountPullRequestsResponse(
    @SerializedName("total_count")
    var amountPullRequests: Int = 0
)
