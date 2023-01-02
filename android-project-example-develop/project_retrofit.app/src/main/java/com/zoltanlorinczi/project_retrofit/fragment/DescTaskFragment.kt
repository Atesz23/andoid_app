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
import java.text.SimpleDateFormat
import java.util.*

class DescTaskFragment : Fragment() {

    val args : DescTaskFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navbartitle: TextView = requireActivity().findViewById(R.id.toolbar_title)
        navbartitle.setText("Details");


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_task, container, false)
        val task = args.task
        val timestamp = task.createdTime/1000
        // Létrehozunk egy SimpleDateFormat objektumot az átalakítandó dátum formátumához
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        // Átalakítjuk a timestamp dátumot egy Date objektummá
        val date = Date(timestamp * 1000L)
        // Formáztatjuk a dátumot a megadott formátumban
        val dateString = formatter.format(date)

        val detail2: TextView = view.findViewById(R.id.detail9)
        detail2.setText("Task Id: ${task.id.toString()}");
        val detail3: TextView = view.findViewById(R.id.detail3)
        detail3.setText(task.description);
        val detail4: TextView = view.findViewById(R.id.detail4)
        detail4.setText("Created by User Id: ${task.createdByUserID.toString()}" );
        val detail5: TextView = view.findViewById(R.id.detail5)
        detail5.setText("Assigned To User ID: ${task.assignedToUserID.toString()}");
        val detail6: TextView = view.findViewById(R.id.detail6)
        detail6.setText("Department ID: ${task.departmentID.toString()}");
        val detail7: TextView = view.findViewById(R.id.detail7)
        detail7.setText("Priority: ${task.priority.toString()}");
//        val detail8: TextView = view.findViewById(R.id.detail8)
//        detail8.setText(task.progress);
        val detail8: TextView = view.findViewById(R.id.detail8)
        detail8.setText("Created date: ${dateString}");
        val detail9: TextView = view.findViewById(R.id.detail10)
        detail9.setText("Task Name: ${task.title}");

        return view
    }
}