package com.espy.onecallsys.api.models.order

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

class OrderResponse(
    @SerialName("data")
    var data: String? = ""
) : ResponseBase()