package com.espy.onecallsys.api.models.shops

import com.espy.onecallsys.api.models.ResponseBase
import com.espy.onecallsys.ui.shops.models.ShopPayPaidAmountRepoData
import kotlinx.serialization.SerialName

data class ShopCollectionPaidAmountResp(
    @SerialName("data")
    var data: List<ShopPayPaidAmountRepoData>
) : ResponseBase()
