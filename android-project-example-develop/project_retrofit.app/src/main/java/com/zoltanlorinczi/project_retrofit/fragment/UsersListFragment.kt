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
import com.zoltanlorinczi.project_retrofit.adapter.UsersListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GroupResponse
import com.zoltanlorinczi.project_retrofit.api.model.UserResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.*

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/2/2021
 */
class UsersListFragment : Fragment(R.layout.fragment_users_list), UsersListAdapter.OnItemClickListener,
        UsersListAdapter.OnItemLongClickListener {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

//    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var userViewModel: UserViewModel
//    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UserViewModelFactory(ThreeTrackerRepository())
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
        val navbartitle: TextView = requireActivity().findViewById(R.id.toolbar_title)
        navbartitle.setText("Users");
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_users_list, container, false)
        recyclerView = view.findViewById(R.id.users_view)
        setupUserView()
        userViewModel.products.observe(viewLifecycleOwner) {
            Log.d(TAG, "User list = $it")
            adapter.setData(userViewModel.products.value as ArrayList<UserResponse>)
            adapter.notifyDataSetChanged()
        }



        return view
    }

    private fun setupUserView() {
        adapter = UsersListAdapter(ArrayList(), requireContext(), this, this)
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
        userViewModel.getUser()
        super.onResume()
    }
}