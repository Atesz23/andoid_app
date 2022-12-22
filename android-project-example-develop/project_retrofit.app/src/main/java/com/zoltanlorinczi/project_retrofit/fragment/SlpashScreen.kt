package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.AddTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModelFactory

class SlpashScreen : Fragment() {
    private lateinit var taskViewModel: TasksViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var factory = TasksViewModelFactory(ThreeTrackerRepository());
        taskViewModel = ViewModelProvider(requireActivity(),factory)[TasksViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        taskViewModel.getTasks();

        taskViewModel.isLogged.observe(viewLifecycleOwner) {
            if (it == 0) {
                //find nav to tasklist
            } else {
                //find nav to loggin
            }
        }
        return inflater.inflate(R.layout.fragment_slpash_screen, container, false)
    }
}