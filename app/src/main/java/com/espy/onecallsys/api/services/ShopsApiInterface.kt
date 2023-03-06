package com.espy.onecallsys.api.services

import com.espy.onecallsys.api.HttpEndPoints
import com.espy.onecallsys.api.models.ResponseBase
import com.espy.onecallsys.api.models.TempResponse
import com.espy.onecallsys.api.models.products.TodayMyOrderRequest
import com.espy.onecallsys.api.models.products.TodayMyOrdersResp
import com.espy.onecallsys.api.models.shops.*
import com.espy.onecallsys.api.models.shops.DeliveryShopRequest
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import retrofit2.http.*

interface ShopsApiInterface {

    @POST(HttpEndPoints.ONECALL_SHOPS_LIST)
    fun getShopsListAsync(
        @Body shopListRequest: ShopListRequest
    ): Deferred<Shops>

    @POST(HttpEndPoints.ONECALL_SHOP_FEEDBACK)
    fun sendFeedbackAsync(
        @Body feedbackRequest: FeedbackRequest
    ): Deferred<FeedbackResponse>

    @POST(HttpEndPoints.ONECALL_SHOP_OUTSTANDING)
    fun getShopOutstandingAsync(
        @Body outstandingRequest: OutstandingRequest
    ): Deferred<OutstandingResponse>

    @Multipart
    @POST(HttpEndPoints.ONECALL_SHOP_PAY_COLLECTION)
    fun submitPaymentCollectionAsync(
        @Part shop_login_id: MultipartBody.Part,
        @Part amount: MultipartBody.Part,
        @Part staff_login_id: MultipartBody.Part,
        @Part pay_type: MultipartBody.Part,
        @Part image: MultipartBody.Part,
        @Part chequenumber: MultipartBody.Part,
        @Part chequedate: MultipartBody.Part
    ): Deferred<TempResponse>

    @POST(HttpEndPoints.ONECALL_SHOP_IN)
    fun shopInAsync(
        @Body shopInRequest: ShopInRequest
    ): Deferred<ResponseBase>

    @POST(HttpEndPoints.ONECALL_SHOP_OUT)
    fun shopOutAsync(
        @Body shopOutRequest: ShopOutRequest
    ): Deferred<ResponseBase>

    @Multipart
    @POST(HttpEndPoints.ONECALL_SHOP_ADD_SHOP)
    fun uploadNewShopAsync(
        @Part loginId: MultipartBody.Part,
        @Part partShopName: MultipartBody.Part,
        @Part partShopReg: MultipartBody.Part,
        @Part partShopPrimaryNo: MultipartBody.Part,
        @Part partShopSecondaryNo: MultipartBody.Part,
        @Part partShopAddress: MultipartBody.Part,
        @Part partShopLat: MultipartBody.Part,
        @Part partShopLon: MultipartBody.Part,
        /*@Part partShopEmail: MultipartBody.Part,*/
        @Part partShopRoute: MultipartBody.Part,
        @Part addedBy: MultipartBody.Part,
        @Part partShopOutstanding: MultipartBody.Part,
        @Part image: MultipartBody.Part
    ): Deferred<TempResponse>

    @GET(HttpEndPoints.ONECALL_ROUTE_LIST)
    fun getRoutesListAsync(): Deferred<Routes>

    @POST(HttpEndPoints.ONECALL_DELIVERY_SHOPS_LIST)
    fun getDeliveryShopsListAsync(
        @Body deliveryShopRequest: DeliveryShopRequest
    ): Deferred<DeliveryShops>

    @POST(HttpEndPoints.ONECALL_PENDING_ORDERS)
    fun getPendingOrdersAsync(
        @Body pendingOrders: PendingOrderRequest
    ): Deferred<PendingOrderResponse>

    @POST(HttpEndPoints.ONECALL_PENDING_ORDER_ITEMS)
    fun getPendingOrderItemsAsync(
        @Body orderItems: OrderItemRequest
    ): Deferred<OrderItemResponse>

    @POST(HttpEndPoints.ONECALL_SHOPS_LIST)
    fun getAllShopSlistAsync(
        @Body shopListRequest: ShopListRequest
    ): Deferred<Shops>

    @POST(HttpEndPoints.ONECALL_DELIVERY_SHOPS_LIST2)
    fun getDeliveryShopsListAsync(
        @Body deliveryShopRequest: DeliveryShopsRequest
    ): Deferred<DeliveryShops>

    @POST(HttpEndPoints.ONECALL_TODAY_MY_ORDERS_LIST)
    fun getTodayMyOrdersAsync(
        @Body todayMyOrderRequest: TodayMyOrderRequest
    ): Deferred<TodayMyOrdersResp>

    @POST(HttpEndPoints.ONECALL_SHOP_COLLECTION_HISTORY_LIST)
    fun getShopCollectionHistoryAsync(
        @Body request: ShopCollectionHistoryRequest
    ): Deferred<ShopCollectionHistoryResp>


    @POST(HttpEndPoints.ONECALL_SHOP_CREATE_PAID_AMOUNT)
    fun submitCreatePaidAmountAsync(
        @Body request: PaidAmountRequestModel
    ): Deferred<TempResponse>



    @POST(HttpEndPoints.ONECALL_SHOP_COLLECTION_PAID_AMOUNT_LIST)
    fun getShopCollectionPaidAmountAsync(
        @Body request: ShopCollectionPaidAmountRequest
    ): Deferred<ShopCollectionPaidAmountResp>
}