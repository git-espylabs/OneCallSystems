package com.espy.onecallsys.api.models.products

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class Products(
    @SerialName("data")
    var data: List<Product> = listOf()
) : ResponseBase()
