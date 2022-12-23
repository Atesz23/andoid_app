package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ProfileResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var profile : MutableLiveData<ProfileResponse?> = MutableLiveData()

    var name : String? = ""


    init {
        getMyProfile()
    }

    private fun getMyProfile() {
        Log.d("ProfileViewModel","getMyProfile() called")
        viewModelScope.launch {
            try {
                val token : String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getProfile(it)
                }
                if (response?.isSuccessful == true) {
                    Log.d(TAG,"Get my profile response: ${response.body()}")
                    val myProfile = response.body()
                    myProfile?.let {
                        name = it.firstName
                        profile.value = it
                    }
                } else {
                    Log.d(TAG, "Get my user error message: ${response?.errorBody()}")
                }
            } catch (e: Exception){
                Log.d(ProfileViewModel.TAG, "ProfileViewModel - getMyProfile() failed with exception: ${e.message}")
            }


        }
    }
}