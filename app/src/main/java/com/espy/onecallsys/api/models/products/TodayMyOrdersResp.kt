package com.espy.onecallsys.api.models.products

import com.espy.onecallsys.api.models.ResponseBase
import com.espy.onecallsys.ui.products.models.TodayMyOrder
import kotlinx.serialization.SerialName

data class TodayMyOrdersResp(
    @SerialName("data")
    var data: List<TodayMyOrder>
) : ResponseBase()
