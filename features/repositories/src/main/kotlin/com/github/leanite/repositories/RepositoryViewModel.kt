package com.github.leanite.repositories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.leanite.data.interaction.GetRepositoriesInteractor
import com.github.leanite.model.Repository
import kotlinx.coroutines.launch

class RepositoryViewModel(
    private val getRepositoriesInteractor: GetRepositoriesInteractor, application: Application
) : AndroidViewModel(application) {

    private val getRepositoriesEvent = MutableLiveData<GetRepositoriesViewEvent>()
    val viewGetRepositoriesEvent: LiveData<GetRepositoriesViewEvent> = getRepositoriesEvent

    val repositories: MutableList<Repository> = ArrayList()
    var currentPage: Int = 1

    fun getRepositories(pageNumber: Int) { //TODO: loading e timeout!!
        viewModelScope.launch {
            try {
                val response = getRepositoriesInteractor.execute(pageNumber)

                getRepositoriesEvent.value = GetRepositoriesViewEvent.Success(response)
            } catch (exception: Exception) {
                getRepositoriesEvent.value = GetRepositoriesViewEvent.Error(exception)
            }
        }
    }

    fun resetPage() {
        currentPage = 1
    }

    fun clearRepositories() = repositories.clear()

    fun incrementPage() {
        currentPage += 1
    }

    fun appendRepositories(repositories: List<Repository>) = this.repositories.addAll(repositories)
}