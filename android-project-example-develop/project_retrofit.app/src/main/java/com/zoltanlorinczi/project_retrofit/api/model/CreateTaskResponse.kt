package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
data class CreateTaskResponse(
    @SerializedName("message")
    var message: String,
) {
    override fun toString(): String {
        return "CreateTaskResponse(" +
                "message='$message'"
    }
}