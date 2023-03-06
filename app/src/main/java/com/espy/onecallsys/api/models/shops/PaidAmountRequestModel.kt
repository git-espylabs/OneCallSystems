package com.espy.onecallsys.api.models.shops

import com.google.gson.annotations.SerializedName

data class PaidAmountRequestModel(

    @SerializedName("shop_login_id")
    var shop_login_id: String = "",
    @SerializedName("amount")
    var amount: String = "",
    @SerializedName("payment_mode")
    var payment_mode: String = "",
    @SerializedName("pay_remarks")
    var pay_remarks: String = "",
    @SerializedName("note")
    var note: String = "",

    @SerializedName("login_id")
    var login_id: String = "",
)
