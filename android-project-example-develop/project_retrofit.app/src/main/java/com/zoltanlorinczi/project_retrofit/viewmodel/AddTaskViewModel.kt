package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.CreateTaskRequest
import com.zoltanlorinczi.project_retrofit.api.model.LoginRequestBody
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
class AddTaskViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var token: MutableLiveData<String> = MutableLiveData()
    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun addTask(title: String, description: String,assigneeToUserId: Int,priority: Int,dedline: Long,departmentId: Int,status: Int) {
        val requestBody = CreateTaskRequest(title, description,assigneeToUserId,priority,dedline,departmentId,status)
        viewModelScope.launch {
            executeAddTask(requestBody)
        }
    }

    private suspend fun executeAddTask(requestBody: CreateTaskRequest) {
        try {
            val token: String? = App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
            val response = token?.let {
                repository.addTask(it,requestBody)
            }

            if (response?.isSuccessful == true) {
                Log.d(TAG, "Add Task response: ${response.body()}")
            } else {
                Log.d(TAG, "Add Task error response: ${response?.message()}")
                isSuccessful.value = false
            }
        } catch (e: Exception) {
            Log.d(TAG, "LoginViewModel - login() failed with exception: ${e.message}")
            isSuccessful.value = false
        }
    }
}