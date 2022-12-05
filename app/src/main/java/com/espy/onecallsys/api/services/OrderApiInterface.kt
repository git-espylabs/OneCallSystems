package com.espy.onecallsys.api.services

import com.espy.onecallsys.api.HttpEndPoints
import com.espy.onecallsys.api.models.order.OrderRequestMaster
import com.espy.onecallsys.api.models.order.OrderResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApiInterface {

    @POST(HttpEndPoints.ONECALL_ORDER_POST)
    fun uploadOrderAsync(
        @Body orderRequestMaster: OrderRequestMaster
    ): Deferred<OrderResponse>
}