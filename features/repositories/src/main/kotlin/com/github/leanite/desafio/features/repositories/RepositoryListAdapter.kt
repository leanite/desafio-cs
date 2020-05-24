package com.github.leanite.desafio.features.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.leanite.model.Repository
import kotlinx.android.synthetic.main.item_list_repository.view.*

class RepositoryListAdapter(
    private val items: List<Repository>,
    private val onItemClick: (position: Int) -> Unit,
    private val onUserAvatarClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RepositoryListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListViewHolder {
        return RepositoryListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_repository, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) =
        holder.bind(items[position], onItemClick, onUserAvatarClick)

    override fun getItemCount(): Int = items.size
}

class RepositoryListViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

    fun bind(item: Repository,
             onItemClick: (position: Int) -> Unit, onUserAvatarClick: (position: Int) -> Unit) {

        //TODO: extrair e melhorar esse método feio
        containerView.apply {
            item.owner.avatarUrl?.let {
                containerMainInfo.loadImage(it)
            }
            containerMainInfo.title = item.fullName
            containerMainInfo.description = item.description
            tvStars.text = item.stars.toString()
            tvForks.text = item.forks.toString()
            tvIssues.text = item.issues.toString()

            setOnClickListener {
                onItemClick(adapterPosition)
            }
//            ivUserProfile.setOnClickListener {
//                onUserAvatarClick(adapterPosition)
//            }
        }
    }
}