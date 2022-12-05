package com.espy.onecallsys.api.models.login

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class ProfileResponse(
    @SerialName("data")
    var data: List<Profile> = listOf()
) : ResponseBase()
