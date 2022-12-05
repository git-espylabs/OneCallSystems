package com.espy.onecallsys.api.models.products

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class Categories(
    @SerialName("data")
    var data: List<Category> = listOf()
) : ResponseBase()
