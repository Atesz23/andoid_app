package com.zoltanlorinczi.project_retrofit.api

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
object BackendConstants {

    /**
     * Project backend base URL.
     */
    const val BASE_URL = "http://tracker-3track.a2hosted.com/"

    /**
     * Specific URL segments, which will be concatenated with the base URL.
     */
    const val LOGIN_URL = "login"
    const val GET_TASKS_URL = "task/getTasks"
    const val GET_GROUP_URL = "department"
    const val ADD_TASK_URL = "task/create"
    const val EDIT_TASK_URL = "task/update"
    const val GET_PROFILE_URL = "user"
    const val GET_USERS_URL = "users"
    const val GET_ACTIVITIES_URL = "activity/getActivities"

    /**
     * Header values.
     */
    const val HEADER_TOKEN = "token"


}