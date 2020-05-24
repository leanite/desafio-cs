package com.github.leanite.repositories

import com.github.leanite.model.Repository
import java.lang.Exception

sealed class GetRepositoriesViewEvent {
    data class Loading(val show: Boolean) : GetRepositoriesViewEvent()
    data class Success(val repositories: List<Repository>) : GetRepositoriesViewEvent()
    data class Error (val exception: Exception) : GetRepositoriesViewEvent()
    data class Timeout (val exception: Exception) : GetRepositoriesViewEvent()
}