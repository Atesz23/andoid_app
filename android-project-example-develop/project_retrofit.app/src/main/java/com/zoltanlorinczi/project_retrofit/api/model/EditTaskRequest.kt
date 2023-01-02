package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
data class EditTaskRequest(
    @SerializedName("taskId")
    var taskId: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("assigneeToUserId")
    var assigneeToUserId: Int,
    @SerializedName("priority")
    var priority: Int,
    @SerializedName("dedline")
    var dedline: Long,
    @SerializedName("departmentId")
    var departmentId: Int,
    @SerializedName("status")
    var status: Int
)