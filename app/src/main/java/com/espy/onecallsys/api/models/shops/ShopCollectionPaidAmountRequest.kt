package com.espy.onecallsys.api.models.shops

import com.google.gson.annotations.SerializedName

data class ShopCollectionPaidAmountRequest(
    @SerializedName("shop_login_id")
    var shop_login_id: String = "",

    /*@SerializedName("from")
    var from: String = "",
    @SerializedName("to")
    var to: String = ""*/
)
