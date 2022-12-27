package com.zoltanlorinczi.project_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/6/2021
 */
class UserViewModelFactory(private val repository: ThreeTrackerRepository) : Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}