package com.espy.onecallsys.api.models.common

import com.espy.onecallsys.api.models.ResponseBase
import kotlinx.serialization.SerialName

data class PaymentTypes(
    @SerialName("data")
    var data: List<PaymentType> = listOf()
) : ResponseBase()
