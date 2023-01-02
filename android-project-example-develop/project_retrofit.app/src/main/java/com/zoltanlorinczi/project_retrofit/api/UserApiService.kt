package com.zoltanlorinczi.project_retrofit.api

import com.zoltanlorinczi.project_retrofit.api.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Retrofit web service API.
 *
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): Response<LoginResponse>

    @GET(BackendConstants.GET_TASKS_URL)
    suspend fun getTasks(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<TaskResponse>>

    @GET(BackendConstants.GET_GROUP_URL)
    suspend fun getGroup(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<GroupResponse>>

    @GET(BackendConstants.GET_PROFILE_URL)
    suspend fun getProfile(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<ProfileResponse>

    @GET(BackendConstants.GET_USERS_URL)
    suspend fun getUser(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<UserResponse>>

    @GET(BackendConstants.GET_ACTIVITIES_URL)
    suspend fun getActivities(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<ActivitiesResponse>>

    @POST(BackendConstants.ADD_TASK_URL)
    suspend fun addTask(@Header(BackendConstants.HEADER_TOKEN) token: String,@Body loginRequest: CreateTaskRequest): Response<CreateTaskResponse>

    @POST(BackendConstants.EDIT_TASK_URL)
    suspend fun editTask(@Header(BackendConstants.HEADER_TOKEN) token: String,@Body loginRequest: EditTaskRequest): Response<EditTaskResponse>
}