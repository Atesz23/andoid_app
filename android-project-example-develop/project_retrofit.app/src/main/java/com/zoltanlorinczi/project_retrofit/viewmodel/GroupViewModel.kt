package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GroupResponse
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/6/2021
 */
class GroupViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var products: MutableLiveData<List<GroupResponse>> = MutableLiveData()
    var isLogged : MutableLiveData<Int> = MutableLiveData();
    init {
        getGroup()
    }

    public fun getGroup() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                        SharedPreferencesManager.KEY_TOKEN,
                        "Empty token!"
                )
                val response = token?.let {
                    repository.getGroup(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get tasks response: ${response.body()}")
                    isLogged.value = 0;
                    val tasksList = response.body()
                    tasksList?.let {
                        products.value = tasksList
                    }
                } else {
                    isLogged.value = 1;
                    Log.d(TAG, "Get tasks error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                isLogged.value = 1;
                Log.d(TAG, "TasksViewModel - getTasks() failed with exception: ${e.message}")
            }
        }
    }
}