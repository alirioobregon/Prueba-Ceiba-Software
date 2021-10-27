package com.example.pruebaceibasoftware.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaceibasoftware.R
import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.databinding.PostListItemBinding

class PostAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currentList = ArrayList<Posts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PostListItemBinding.bind(itemView)
        fun bind(postVO: Posts) {
            binding.title.text = postVO.title
            binding.body.text = postVO.body
        }
    }

    fun setData(newList: ArrayList<Posts>) {
        val diffUtils = PostsDiffUtils(currentList, newList)
        val result = DiffUtil.calculateDiff(diffUtils)
        currentList.clear()
        currentList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}

class PostsDiffUtils(private val oldList: ArrayList<Posts>, private val newList: ArrayList<Posts>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}