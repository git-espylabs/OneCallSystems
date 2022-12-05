package com.espy.onecallsys.api.models.shops

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class FeedbackResponse(
    @SerialName("data")
    var data: String? = ""
) : ResponseBase()
