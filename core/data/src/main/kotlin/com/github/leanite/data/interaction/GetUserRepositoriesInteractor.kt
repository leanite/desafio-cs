package com.github.leanite.data.interaction

import com.github.leanite.data.api.RepositoryApi
import com.github.leanite.model.Repository

class GetUserRepositoriesInteractor(private val api: RepositoryApi) {
    suspend fun execute(request: GetUserRepositoriesRequest): List<Repository> =
        api.getUserRepositoriesAtPageNumber(request.repositoryUsername, request.pageNumber)
}

data class GetUserRepositoriesRequest(
    val repositoryUsername: String,
    val pageNumber: Int
)