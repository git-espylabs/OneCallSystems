package com.espy.onecallsys.api.models.order

import com.google.gson.annotations.SerializedName

data class OrderRequestMaster(
    @SerializedName("added_id")
    var added_id: String = "",
    @SerializedName("shop_id")
    var shop_id: String = "",
    @SerializedName("order_status")
    var order_status: String = "",
    @SerializedName("total_amount")
    var total_amount: String = "",
    @SerializedName("total_qty")
    var total_qty: String = "",
    @SerializedName("discount")
    var discount: String = "",

    @SerializedName("remarks")
    var remarks: String = "",




    @SerializedName("order_transaction")
    var order_transaction: List<OrderRequestTrans?>
)
