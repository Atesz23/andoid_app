package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.AddTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.AddTaskViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.LoginViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.LoginViewModelFactory
import kotlinx.android.synthetic.main.add_task_fragment.*
import kotlinx.android.synthetic.main.fragment_tasks_list.*

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/12/2021
 */
class AddTaskFragment : Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var addTaskViewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddTaskViewModelFactory(ThreeTrackerRepository())
        addTaskViewModel = ViewModelProvider(this, factory)[AddTaskViewModel::class.java]
        val navbartitle: TextView = requireActivity().findViewById(R.id.toolbar_title)
        navbartitle.setText("ADD Tasks");
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_task_fragment, container, false)

        val taskName: EditText = view.findViewById(R.id.inputTaskName)
        val taskDesc: EditText = view.findViewById(R.id.inputTaskDesc)
        val button: Button = requireActivity().findViewById(R.id.saveTaskButton)

        val addIcon : ImageView = requireActivity().findViewById(R.id.add_icon)
        addIcon.visibility = View.GONE
        val seach : ImageView = requireActivity().findViewById(R.id.right_icon)
        seach.visibility = View.GONE

        button.visibility = View.VISIBLE

        Log.d(
            TAG,
            "token = " + App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
        )

        button.setOnClickListener {
            val Name = taskName.text.toString()
            val Desc = taskDesc.text.toString()
            addTaskViewModel.addTask(Name,Desc,69,1,1672684299725,2,1)

            addTaskViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d("TaskAdd", "Task Added in successfully = $it")
                findNavController().navigate(R.id.listFragment)
                if (it) {

                }
            }
            findNavController().navigate(R.id.listFragment)
        }

        return view
    }
}