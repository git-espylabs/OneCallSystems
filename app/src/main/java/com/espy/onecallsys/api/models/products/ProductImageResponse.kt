package com.espy.onecallsys.api.models.products

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class ProductImageResponse(
    @SerialName("data")
    var data: List<ProductImage> = listOf()
) : ResponseBase()
