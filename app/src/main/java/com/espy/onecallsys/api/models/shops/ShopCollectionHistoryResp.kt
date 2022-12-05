package com.espy.onecallsys.api.models.shops

import com.espy.onecallsys.api.models.ResponseBase
import com.espy.onecallsys.ui.shops.models.ShopPayHistory
import kotlinx.serialization.SerialName

data class ShopCollectionHistoryResp(
    @SerialName("data")
    var data: List<ShopPayHistory>
) : ResponseBase()
