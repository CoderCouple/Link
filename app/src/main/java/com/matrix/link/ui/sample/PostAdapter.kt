package com.matrix.link.ui.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matrix.link.R
import com.matrix.link.network.model.Post

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var postResponseList = mutableListOf<Post>()

    fun setPostResponseList(postList: MutableList<Post>) {
        postResponseList = postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_post_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(position, postResponseList)
    }

    override fun getItemCount(): Int {
        return postResponseList.size;
    }
}


class PostViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var userId = itemView.findViewById<TextView>(R.id.userId)
    var title = itemView.findViewById<TextView>(R.id.userId)

    fun bind(position: Int, postResponseList: List<Post>) {
        this.userId.text = postResponseList[position].id.toString()
        this.title.text = postResponseList[position].title
    }
}