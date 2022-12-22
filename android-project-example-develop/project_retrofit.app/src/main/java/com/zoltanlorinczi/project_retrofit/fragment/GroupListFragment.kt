package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.adapter.GroupListAdapter
import com.zoltanlorinczi.project_retrofit.adapter.TasksListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GroupResponse
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.*
import kotlinx.android.synthetic.main.add_task_fragment.*

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/2/2021
 */
class GroupListFragment : Fragment(R.layout.fragment_group_list), GroupListAdapter.OnItemClickListener,
        GroupListAdapter.OnItemLongClickListener {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

//    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var groupViewModel: GroupViewModel
//    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GroupListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = GroupViewModelFactory(ThreeTrackerRepository())
        groupViewModel = ViewModelProvider(this, factory)[GroupViewModel::class.java]
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_group_list, container, false)
        recyclerView = view.findViewById(R.id.groups_view)
        setupGroupView()
        groupViewModel.products.observe(viewLifecycleOwner) {
            Log.d(TAG, "Tasks list = $it")
            adapter.setData(groupViewModel.products.value as ArrayList<GroupResponse>)
            adapter.notifyDataSetChanged()
        }



        return view
    }

    private fun setupGroupView() {
        adapter = GroupListAdapter(ArrayList(), requireContext(), this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                )
        )
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun onResume() {
        groupViewModel.getGroup();
        super.onResume()
    }
}