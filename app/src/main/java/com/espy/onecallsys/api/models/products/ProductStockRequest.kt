package com.espy.onecallsys.api.models.products

import com.google.gson.annotations.SerializedName

data class ProductStockRequest(
    @SerializedName("batchid")
    var batchid: String = ""
)