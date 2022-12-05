package com.espy.onecallsys.api.models.common

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class ExpenseTypes(
    @SerialName("data")
    var data: List<ExpenseType> = listOf()
) : ResponseBase()
