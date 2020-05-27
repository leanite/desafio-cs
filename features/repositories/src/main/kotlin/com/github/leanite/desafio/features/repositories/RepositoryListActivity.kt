package com.github.leanite.desafio.features.repositories

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.leanite.core.base.BaseActivity
import com.github.leanite.core.base.EndlessScrollListener
import com.github.leanite.core.base.ListItemDecoration
import com.github.leanite.model.Repository
import com.github.leanite.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_repository_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

import java.lang.Exception

class RepositoryListActivity : BaseActivity() {

    private lateinit var endlessScrollListener: EndlessScrollListener
    private val viewModel: RepositoryViewModel by viewModel()

    override fun getActivityLayout(): Int = R.layout.activity_repository_list

    override fun setupInjection() = RepositoryModule.inject()

    override fun onSetupDone() {
        getRepositories()
    }

    override fun setupToolbar() {
        super.setupToolbar()
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.title = "Java" //TODO flavor language
        }
    }

    override fun setupViews() {
        setupRefreshContainer()
        setupRepositoriesRecyclerViewAdapter()
        setupRepositoriesRecyclerView()
    }

    override fun setupObservers() {
        viewModel.viewGetRepositoriesEvent.observe(this, Observer {
            when(it) { //TODO: timeout
                is GetRepositoriesViewEvent.Loading -> {
                    refreshContainer.isRefreshing = it.show
                }
                is GetRepositoriesViewEvent.Success -> updateRepositories(it.repositories)
                is GetRepositoriesViewEvent.Error -> showError(it.exception)
            }
        })
    }

    private fun setupRefreshContainer() {
        refreshContainer.setOnRefreshListener {
            viewModel.resetPage()
            viewModel.clearRepositories()
            rvRepositoryList.adapter?.notifyDataSetChanged()
            endlessScrollListener.reset()
            getRepositories()
        }

        refreshContainer.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.colorPrimary),
            ContextCompat.getColor(this, R.color.colorAccent)
        )
    }

    private fun setupRepositoriesRecyclerViewAdapter() {
        val repositoriesAdapter = RepositoryListAdapter(
            viewModel.repositories,
            onItemClick = { position: Int ->
                goToPullRequestActivity(viewModel.repositories[position])
            },
            onUserAvatarClick = { position: Int ->
                goToUserDetailsActivity(viewModel.repositories[position].owner)
            }
        )
        rvRepositoryList.adapter = repositoriesAdapter
    }

    private fun setupRepositoriesRecyclerView() {
        endlessScrollListener = EndlessScrollListener {
            viewModel.incrementPage()
            getRepositories()
        }
        rvRepositoryList.layoutManager = LinearLayoutManager(this)
        rvRepositoryList.addItemDecoration(ListItemDecoration(getDrawable(R.drawable.item_list_divider)))
        rvRepositoryList.addOnScrollListener(endlessScrollListener)
    }

    private fun goToPullRequestActivity(repository: Repository) { //TODO ViewModel + Navigation
//        val intent = Intent(this, PullRequestListActivity::class.java)
//
//        val args = createPullRequestActivityArgs(repository)
//        intent.putExtra(PullRequestListActivity.ARGS, args)
//
//        startActivity(intent)
    }

//    private fun createPullRequestActivityArgs(repository: Repository): Bundle {
//        val bundle = Bundle()
//
//        bundle.putString(PullRequestListActivity.REPOSITORY_USERNAME, repository.owner.username)
//        bundle.putString(PullRequestListActivity.REPOSITORY_NAME, repository.name)
//
//        return bundle
//    }

    private fun goToUserDetailsActivity(user: User) { //TODO ViewModel + Navigation
//        val intent = Intent(this, UserDetailsActivity::class.java)
//
//        val args = createUserDetailsActivityArgs(user)
//        intent.putExtra(UserDetailsActivity.ARGS, args)
//
//        startActivity(intent)
    }

//    private fun createUserDetailsActivityArgs(user: User): Bundle {
//        val bundle = Bundle()
//
//        bundle.putString(UserDetailsActivity.USER_USERNAME, user.username)
//
//        return bundle
//    }

    private fun updateRepositories(repositories: List<Repository>) {
        viewModel.appendRepositories(repositories)
        rvRepositoryList.adapter?.notifyDataSetChanged()
    }

    private fun getRepositories() {
        viewModel.getRepositories(viewModel.currentPage)
    }

    private fun showError(exception: Exception) {
        exception.message?.let { //TODO: remover mensagem de exception aqui
            Snackbar.make(rvRepositoryList, it, Snackbar.LENGTH_LONG).show()
        }
    }
}

