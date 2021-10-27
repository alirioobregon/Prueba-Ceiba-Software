package com.example.pruebaceibasoftware.data.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaceibasoftware.R
import com.example.pruebaceibasoftware.database.schemas.Users
import com.example.pruebaceibasoftware.databinding.UserListItemBinding

class MainAdapter(private val onUser: OnUser) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currentList = ArrayList<Users>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(currentList[position], onUser)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserListItemBinding.bind(itemView)
        fun bind(userVO: Users, onUser: OnUser) {
            binding.nameUser.text = userVO.name
            binding.userPhone.text = userVO.phone
            binding.userEmail.text = userVO.email

            binding.showPost.setOnClickListener {
                onUser.showPost(userVO)
            }
        }
    }

    fun setData(newList: ArrayList<Users>) {
        val diffUtils = UsersDiffUtils(currentList, newList)
        val result = DiffUtil.calculateDiff(diffUtils)
        currentList.clear()
        currentList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    interface OnUser {
        fun showPost(user: Users)
    }
}

class UsersDiffUtils(private val oldList: ArrayList<Users>, private val newList: ArrayList<Users>) : DiffUtil.Callback() {

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