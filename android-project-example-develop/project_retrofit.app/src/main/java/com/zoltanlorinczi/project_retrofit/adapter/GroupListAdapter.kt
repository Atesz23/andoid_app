package com.zoltanlorinczi.project_retrofit.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.model.GroupResponse
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/6/2021
 */
class GroupListAdapter(
    private var list: ArrayList<GroupResponse>,
    private val context: Context,
    private val listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener
) :
    RecyclerView.Adapter<GroupListAdapter.SimpleDataViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    open inner class SimpleDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

        override fun onLongClick(v: View?): Boolean {
            TODO("Not yet implemented")
        }
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : SimpleDataViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val GroupTitleTextView: TextView = itemView.findViewById(R.id.group_title_view)
        val Version: TextView = itemView.findViewById(R.id.version)
        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)

        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleDataViewHolder {
        return when (viewType) {
            TaskListItemType.SIMPLE.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.simple_group_list_item, parent, false)
                SimpleDataViewHolder(itemView)
            }
            TaskListItemType.COMPLEX.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.simple_group_list_item, parent, false)
                DataViewHolder(itemView)
            }
            else -> {
                throw IllegalStateException("Type is not supported!")
            }
        }

    }

    override fun getItemViewType(position: Int): Int {


        return TaskListItemType.COMPLEX.value
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: SimpleDataViewHolder, position: Int) {
//        if (getItemViewType(position) == TaskListItemType.COMPLEX.value) {
        val complexHolder = (holder as DataViewHolder)
        val currentItem = list[position]

        complexHolder.GroupTitleTextView.text = currentItem.name
        val userList = currentItem.listOfUsers
        val strBuilder = StringBuilder()
        userList?.forEach {
            val a = it.firstName
            val b = it.lastName
            val aux = a.plus(" ").plus(b)
            strBuilder.appendLine(aux)
        }
        complexHolder.Version.text =strBuilder
//            Glide.with(context)
//                .load(R.drawable.ic_launcher_background)
//                //.load("https://devinit.org/assets/img/profile-fallback.e7a6f788830c.jpg")
//                //.placeholder(R.drawable.ic_launcher_background)
//                .override(100, 100)
//                .into(complexHolder.taskOwnerProfileImage)
//        }
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<GroupResponse>) {
        list = newList
    }

    private enum class TaskListItemType(val value: Int) {
        SIMPLE(0),
        COMPLEX(1)
    }
}