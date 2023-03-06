package com.espy.onecallsys.ui.shops.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class ShopPayPaidAmountRepoData(
    @SerialName("id")
    var id: String? = "",
    @SerialName("shop_login_id")
    var shop_login_id: String? = "",
    @SerialName("date")
    var date: String? = "",
    @SerialName("amount")
    var amount: String? = "0",
    @SerialName("payment_mode")
    var payment_mode: String? = "",
    @SerialName("pay_remarks")
    var pay_remarks: String? = "",
    @SerialName("note")
    var note: String? = "0",
    @SerialName("added_by")
    var added_by: String? = ""


) : Parcelable
