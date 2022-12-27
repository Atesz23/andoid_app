package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GroupResponse
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.api.model.UserResponse
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

    var users: MutableLiveData<List<UserResponse>> = MutableLiveData()

    init {
        getUser()
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
                    val groups = response.body()

                    isLogged.value = 0;
                    val groupList = response.body()
                    groupList?.let {
                        val aux=users.value?.groupBy { it.departmentID }
                        Log.d(TAG,"aux:${aux}")

                        aux?.forEach{ grouping->
                            val aux2 = groupList.find { grouping.key == it.id }
                            aux2?.listOfUsers = grouping.value
                        }
                        products.value = groupList

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

    public fun getUser() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getUser(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get Users response: ${response.body()}")

                    isLogged.value = 0;
                    val usersList = response.body()
                    usersList?.let {

                        users.value = usersList
                        getGroup()
                    }
                } else {
                    isLogged.value = 1;
                    Log.d(TAG, "Get Users error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                isLogged.value = 1;
                Log.d(TAG, "Userviewmodel - getUser() failed with exception: ${e.message}")
            }
        }
    }
}