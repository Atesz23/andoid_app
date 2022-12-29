package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
data class ActivitiesResponse(
    @SerializedName("ID")
    var id: Int,

    @SerializedName("type")
    var type: Int,

    @SerializedName("created_by_user_id")
    var created_by_user_id: Int,

    @SerializedName("sub_type")
    var sub_type: Int,

    @SerializedName("created_time")
    var created_time: Long,

    @SerializedName("sub_ID")
    var sub_ID: Int,

    @SerializedName("sub_user_ID")
    var sub_user_ID: Int,

    @SerializedName("note")
    var note: String,

    @SerializedName("progress")
    var progress :String?,

    var listOfUsers: List<UserResponse>? = null
)
