package com.zoltanlorinczi.project_retrofit.api

import com.zoltanlorinczi.project_retrofit.api.model.*
import retrofit2.Response

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
class ThreeTrackerRepository {

    /**
     * 'suspend' keyword means that this function can be blocking.
     * We need to be aware that we can only call them from within a coroutine or an other suspend function!
     */
    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return RetrofitInstance.USER_API_SERVICE.login(loginRequestBody)
    }
    suspend fun getTasks(token: String): Response<List<TaskResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getTasks(token)
    }
    suspend fun getGroup(token: String): Response<List<GroupResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getGroup(token)
    }
    suspend fun addTask(token:String,createTaskRequestBody: CreateTaskRequest): Response<CreateTaskResponse> {
        return RetrofitInstance.USER_API_SERVICE.addTask(token,createTaskRequestBody)
    }
}