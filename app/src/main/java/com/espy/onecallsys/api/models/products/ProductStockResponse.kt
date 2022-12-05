package com.espy.onecallsys.api.models.products

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class ProductStockResponse(
    @SerialName("data")
    var data: List<ProductStock> = listOf()
) : ResponseBase()