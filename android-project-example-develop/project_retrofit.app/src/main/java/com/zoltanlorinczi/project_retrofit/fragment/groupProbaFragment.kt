package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.AddTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModelFactory

class groupProbaFragment : Fragment() {
    private lateinit var groupFragment: groupProbaFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var factory = TasksViewModelFactory(ThreeTrackerRepository());

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        taskViewModel.getTasks();

//        var languages = resources.getStringArray(R.array.languages)
//        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,languages)

        return inflater.inflate(R.layout.splash_fragment, container, false)
    }
}