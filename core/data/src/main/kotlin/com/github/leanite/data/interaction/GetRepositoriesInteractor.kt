package com.github.leanite.data.interaction

import com.github.leanite.data.api.RepositoryApi
import com.github.leanite.model.Repository
import com.google.gson.annotations.SerializedName

class GetRepositoriesInteractor(val api: RepositoryApi) {

    suspend fun execute(pageNumber: Int): List<Repository> =
        api.getRepositoriesAtPageNumber(pageNumber).repositories
}

data class GetRepositoriesResponse(
    @SerializedName("items")
    var repositories: List<Repository>
)