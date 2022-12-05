package com.espy.onecallsys.api.models.login

import com.google.gson.annotations.SerializedName

data class ProductAllStaffRequest(
    @SerializedName("staffid")
    var staffid: String = ""
)
