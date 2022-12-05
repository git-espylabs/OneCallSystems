package com.espy.onecallsys.api.models.login

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class LeaveResponse(
    @SerialName("data")
    var data: String? = ""
) : ResponseBase()