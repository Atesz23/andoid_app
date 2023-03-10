package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.adapter.TasksListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.AddTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModelFactory

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/2/2021
 */
class TasksListFragment : Fragment(R.layout.fragment_tasks_list), TasksListAdapter.OnItemClickListener,
        TasksListAdapter.OnItemLongClickListener {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TasksListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = TasksViewModelFactory(ThreeTrackerRepository())
        tasksViewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
        val navBar : BottomNavigationView = requireActivity().findViewById(R.id.bottomNavBar)
        navBar.visibility = View.VISIBLE

        val navbartitle: TextView = requireActivity().findViewById(R.id.toolbar_title)
        navbartitle.setText("Tasks");
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_tasks_list, container, false)
        val addIcon : ImageView = requireActivity().findViewById(R.id.add_icon)
        addIcon.visibility = View.VISIBLE
        val seach : ImageView = requireActivity().findViewById(R.id.right_icon)
        seach.visibility = View.VISIBLE
        val addTaskButton: ImageView = requireActivity().findViewById(R.id.add_icon)
        val savetask : Button = requireActivity().findViewById(R.id.saveTaskButton)
        savetask.visibility = View.GONE
//        val listGroubButton: Button = view.findViewById(R.id.listGroubButton)
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        tasksViewModel.tasks.observe(viewLifecycleOwner) {
            Log.d(TAG, "Tasks list = $it")
            adapter.setData(tasksViewModel.tasks.value as ArrayList<TaskResponse>)
            adapter.notifyDataSetChanged()
        }

        addTaskButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addTaskFragment)

        }
//        listGroubButton.setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_groupListFragment)
//
//        }

        return view
    }

    private fun setupRecyclerView() {
        adapter = TasksListAdapter(ArrayList(), requireContext(), this, this)
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
        tasksViewModel.tasks.value?.get(position)?.let{
            val action = TasksListFragmentDirections.actionListFragmentToDescTaskFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
        tasksViewModel.tasks.value?.get(position)?.let{
            val action = TasksListFragmentDirections.actionListFragmentToEditTaskFragment(it)
            findNavController().navigate(action)
        }

    }

    override fun onResume() {
        tasksViewModel.getTasks();
        super.onResume()
    }
}