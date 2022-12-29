package com.zoltanlorinczi.project_retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.model.ActivitiesResponse
import com.zoltanlorinczi.project_retrofit.api.model.GroupResponse
import com.zoltanlorinczi.project_retrofit.api.model.UserResponse
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/6/2021
 */
class ActivitesListAdapter(
    private var list: ArrayList<ActivitiesResponse>,
    private val context: Context,
    private val listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener
) :
    RecyclerView.Adapter<ActivitesListAdapter.SimpleDataViewHolder>() {

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
        val ActivitiesTitleTextView: TextView = itemView.findViewById(R.id.activites_title_view)
        val ActivitiesDateTextView: TextView = itemView.findViewById(R.id.activities_date_view)
        val ActivitiesText: TextView = itemView.findViewById(R.id.activities_text)

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
                    .inflate(R.layout.simple_activities_list_item, parent, false)
                SimpleDataViewHolder(itemView)
            }
            TaskListItemType.COMPLEX.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.simple_activities_list_item, parent, false)
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

        val userList = currentItem.listOfUsers
        val strBuilder = StringBuilder()
        userList?.forEach {
            val a = it.firstName
            val b = it.lastName
            val aux = a.plus(" ").plus(b)
            strBuilder.appendLine(aux)
        }

//        complexHolder.ActivitiesTitleTextView.text = currentItem.id.toString()
        complexHolder.ActivitiesTitleTextView.text = strBuilder
        complexHolder.ActivitiesText.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc gravida laoreet risus, nec fringilla mi elementum bibendum. Suspendisse potenti. Praesent laoreet velit condimentum sollicitudin fringilla. Ut laoreet justo quam, quis euismod tellus scelerisque sed. In ut scelerisque nibh. "

        val timestamp = currentItem.created_time/1000

        // Létrehozunk egy SimpleDateFormat objektumot az átalakítandó dátum formátumához
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        // Átalakítjuk a timestamp dátumot egy Date objektummá
        val date = Date(timestamp * 1000L)

        // Formáztatjuk a dátumot a megadott formátumban
        val dateString = formatter.format(date)
        complexHolder.ActivitiesDateTextView.text = dateString

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
    fun setData(newList: ArrayList<ActivitiesResponse>) {
        list = newList
    }

    private enum class TaskListItemType(val value: Int) {
        SIMPLE(0),
        COMPLEX(1)
    }

}