package com.espy.onecallsys.api.models.products

import com.google.gson.annotations.SerializedName

data class TodayMyOrderRequest(
    @SerializedName("staffid")
    var staffid: String = ""
)
