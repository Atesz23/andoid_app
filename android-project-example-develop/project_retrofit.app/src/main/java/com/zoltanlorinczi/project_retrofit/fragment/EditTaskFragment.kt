package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.EditTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.EditTaskViewModelFactory

class EditTaskFragment : Fragment() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }
    private lateinit var editTaskViewModel: EditTaskViewModel
    val args : EditTaskFragmentArgs by navArgs()
//    val task = args.task
//    val taskid = task.id
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = EditTaskViewModelFactory(ThreeTrackerRepository())
        editTaskViewModel = ViewModelProvider(this, factory)[EditTaskViewModel::class.java]
        val navbartitle: TextView = requireActivity().findViewById(R.id.toolbar_title)
        navbartitle.setText("Edit Tasks");
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_task, container, false)
        val task = args.task
        val taskid = task.id
        val taskName: EditText = view.findViewById(R.id.editTaskName)
        val taskDesc: EditText = view.findViewById(R.id.editTaskDesc)
        val button: Button = requireActivity().findViewById(R.id.saveTaskButton)
        taskName.setText(task.title);
        taskDesc.setText(task.description);
        button.setText("Edit")

        val addIcon : ImageView = requireActivity().findViewById(R.id.add_icon)
        addIcon.visibility = View.GONE
        val seach : ImageView = requireActivity().findViewById(R.id.right_icon)
        seach.visibility = View.GONE

        button.visibility = View.VISIBLE


        button.setOnClickListener {
            val Name = taskName.text.toString()
            val Desc = taskDesc.text.toString()
            editTaskViewModel.editTask(taskid,Name,Desc,69,1,1672684299725,2,1)

            editTaskViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d("TaskAdd", "Task Added in successfully = $it")
                findNavController().navigate(R.id.action_editTaskFragment_to_listFragment)
                if (it) {

                }
            }
            findNavController().navigate(R.id.action_editTaskFragment_to_listFragment)
        }

        return view
    }
}