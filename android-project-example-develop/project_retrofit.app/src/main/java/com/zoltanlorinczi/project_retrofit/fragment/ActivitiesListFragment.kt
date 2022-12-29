package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.adapter.ActivitesListAdapter
import com.zoltanlorinczi.project_retrofit.adapter.UsersListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ActivitiesResponse
import com.zoltanlorinczi.project_retrofit.api.model.GroupResponse
import com.zoltanlorinczi.project_retrofit.api.model.UserResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.*

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/2/2021
 */
class ActivitiesListFragment : Fragment(R.layout.fragment_group_list), ActivitesListAdapter.OnItemClickListener,
        ActivitesListAdapter.OnItemLongClickListener {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

//    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var activitiesViewModel: ActivitiesViewModel
//    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActivitesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ActivitiesViewModelFactory(ThreeTrackerRepository())
        activitiesViewModel = ViewModelProvider(this, factory)[ActivitiesViewModel::class.java]
        val navbartitle: TextView = requireActivity().findViewById(R.id.toolbar_title)
        navbartitle.setText("Activities");
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_activities_list, container, false)
        recyclerView = view.findViewById(R.id.activities_view)
        setupActivitesView()
        activitiesViewModel.products.observe(viewLifecycleOwner) {
            Log.d(TAG, "Activities list = $it")
            adapter.setData(activitiesViewModel.products.value as ArrayList<ActivitiesResponse>)
            adapter.notifyDataSetChanged()
        }



        return view
    }

    private fun setupActivitesView() {
        adapter = ActivitesListAdapter(ArrayList(), requireContext(), this, this)
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
        activitiesViewModel.getActivities()
        super.onResume()
    }
}