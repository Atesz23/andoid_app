package com.zoltanlorinczi.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
data class UserResponse(
    @SerializedName("ID")
    var id: Int,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("email")
    var emailAddress: String,

    @SerializedName("type")
    var userType: Int,

    @SerializedName("location")
    var location: String,

    @SerializedName("phone_number")
    var phoneNumber: String,

    @SerializedName("department_id")
    var departmentID: Int,

    @SerializedName("image")
    var image :String?
)
