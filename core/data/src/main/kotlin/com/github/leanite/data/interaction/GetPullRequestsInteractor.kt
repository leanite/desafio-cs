package com.github.leanite.data.interaction

import com.github.leanite.data.api.PullRequestApi
import com.github.leanite.model.PullRequest

class GetPullRequestsInteractor(private val api: PullRequestApi) {

    suspend fun execute(request: GetPullRequestsRequest): List<PullRequest> =
        api.getPullRequests(
            request.repositoryUsername, request.repositoryName, request.state, request.pageNumber)
}

data class GetPullRequestsRequest(
    val repositoryUsername: String,
    val repositoryName: String,
    val state: String,
    val pageNumber: Int
)