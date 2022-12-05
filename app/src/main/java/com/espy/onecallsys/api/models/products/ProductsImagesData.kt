package com.espy.onecallsys.api.models.products

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class ProductsImagesData(
    @SerialName("data")
    var data: List<ProductImageData> = listOf(),
    @SerialName("count")
    var count: Int = 0,
) : ResponseBase()
