package com.espy.onecallsys.api.models.login

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

class LoginResponse(
    @SerialName("data")
    var data: String? = ""
) : ResponseBase()