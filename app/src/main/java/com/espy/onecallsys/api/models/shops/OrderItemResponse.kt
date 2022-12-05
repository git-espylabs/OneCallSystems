package com.espy.onecallsys.api.models.shops

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class OrderItemResponse(
    @SerialName("data")
var data: List<OrderItem>?
) : ResponseBase()
