package com.espy.onecallsys.ui.shops.view

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.espy.onecallsys.api.MultiPartRequestHelper
import com.espy.onecallsys.api.Result
import com.espy.onecallsys.preference.AppPreferences
import com.espy.onecallsys.repository.ShopRepository
import com.espy.onecallsys.api.models.common.PaymentType
import com.espy.onecallsys.api.models.shops.*
import com.espy.onecallsys.ui.base.BaseViewModel
import com.espy.onecallsys.ui.shops.models.NewShopData
import com.espy.onecallsys.ui.shops.models.NewShopMultiPartData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ShopsViewModel : BaseViewModel() {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val shopRepository = ShopRepository()

    var shopId = ""
    var shopLoginId = ""
    var shopsOutstandingTotal = 0F
    var capPaymentTypes = listOf<PaymentType>()

    private val _feedbackResponse: MutableLiveData<Boolean> = MutableLiveData()
    val feedbackResponse: LiveData<Boolean>
        get() = _feedbackResponse

    private val _shopsOutstandingList: MutableLiveData<Result<List<ShopOutstanding>>> = MutableLiveData()
    val shopsOutstandingList: LiveData<Result<List<ShopOutstanding>>>
        get() = _shopsOutstandingList

    private val _paymentTypesList: MutableLiveData<Result<List<PaymentType>>> = MutableLiveData()
    val paymentTypesList: LiveData<Result<List<PaymentType>>>
        get() = _paymentTypesList

    private val _paymentStatus: MutableLiveData<Boolean> = MutableLiveData()
    val paymentStatus: LiveData<Boolean>
        get() = _paymentStatus

    private val _paidStatus: MutableLiveData<Boolean> = MutableLiveData()
    val paidStatus: LiveData<Boolean>
        get() = _paidStatus

    private val _isShoppedOut: MutableLiveData<Boolean> = MutableLiveData()
    val isShoppedOut: LiveData<Boolean>
        get() = _isShoppedOut

    var _addNewShop: MutableLiveData<Boolean> = MutableLiveData()
    val addNewShop: LiveData<Boolean>
        get() = _addNewShop

    var _shopRoutes: MutableLiveData<List<Route>> = MutableLiveData()
    val shopRoutes: LiveData<List<Route>>
        get() = _shopRoutes

    var _deliveryShopsList: MutableLiveData<com.espy.onecallsys.api.Result<List<DeliveryShop>>> = MutableLiveData()
    val deliveryShopsList: LiveData<com.espy.onecallsys.api.Result<List<DeliveryShop>>>
        get() = _deliveryShopsList

    var _pendingOrderList: MutableLiveData<com.espy.onecallsys.api.Result<List<PendingOrder>>> = MutableLiveData()
    val pendingOrderList: LiveData<com.espy.onecallsys.api.Result<List<PendingOrder>>>
        get() = _pendingOrderList

    var _orderItemsList: MutableLiveData<com.espy.onecallsys.api.Result<List<OrderItem>>> = MutableLiveData()
    val orderItemsList: LiveData<com.espy.onecallsys.api.Result<List<OrderItem>>>
        get() = _orderItemsList



    fun postFeedBack(feedback: String, description: String, location: Location?){
        val feedbackRequest = FeedbackRequest(feedback, description, AppPreferences.userId, shopId, location?.latitude?.toString()?:"0.0", location?.longitude?.toString()?:"0.0")
        viewModelScope.launch {
            shopRepository.postFeedBack(feedbackRequest).collect {
                _feedbackResponse.value = it
            }
        }
    }

    fun getShopOutstanding(shopid: String){
        val outstandingRequest = OutstandingRequest(shopid)
        viewModelScope.launch {
            shopRepository.getShopOutstanding(outstandingRequest).collect {
                _shopsOutstandingList.value = it
                getPaymentTypes()
            }
        }
    }

    private fun getPaymentTypes(){
        viewModelScope.launch {
            shopRepository.getPaymentTypes().collect {
                _paymentTypesList.value = it
            }
        }
    }

    fun submitPaymentCollection(amount: String, pay_type:String, imagepath: String, context: Context, shopid: String, chequenumber: String, chequedate: String){
        val partShopId = MultiPartRequestHelper.createRequestBody("shop_login_id", shopid)
        val partAmount = MultiPartRequestHelper.createRequestBody("amount", amount)
        val partLoginId = MultiPartRequestHelper.createRequestBody("staff_login_id", AppPreferences.userId)
        val partpayType = MultiPartRequestHelper.createRequestBody("pay_type", pay_type)
        val partCheqNo = MultiPartRequestHelper.createRequestBody("cheque_number", chequenumber)
        val partCheqDat = MultiPartRequestHelper.createRequestBody("Chequedate", chequedate)
        val partFile = MultiPartRequestHelper.createFileRequestBody(imagepath, "image", context)
        viewModelScope.launch {
            shopRepository.submitPaymentCollection(partShopId, partAmount, partLoginId,  partpayType, partFile, partCheqNo, partCheqDat).collect {
                _paymentStatus.value = it
            }
        }
    }
    fun tagShopOut(shopid: String, location: Location?){
        val shopOutRequest = ShopOutRequest(AppPreferences.userId, shopid, "1", location?.latitude?.toString()?:"0.0", location?.longitude?.toString()?:"0.0")
        viewModelScope.launch {
            shopRepository.tagShopOut(shopOutRequest).collect {
                _isShoppedOut.value = it
            }
        }
    }

    fun getRouteList(){
        viewModelScope.launch {
            shopRepository.getRoutesList().let {
                if (it is Result.Success && it.data.isNotEmpty()){
                    _shopRoutes.value = it.data
                }else{
                    _shopRoutes.value = listOf()
                }
            }
        }
    }

    fun uploadNewShop(newShopData: NewShopData, context: Context){
        val loginId = MultiPartRequestHelper.createRequestBody("login_id", AppPreferences.userId)
        val added_by = MultiPartRequestHelper.createRequestBody("added_by", AppPreferences.userId)
        val partShopName = MultiPartRequestHelper.createRequestBody("shop_name", newShopData.shopName)
        val partShopReg = MultiPartRequestHelper.createRequestBody("shop_regi_no", newShopData.shopRegNo)
        val partShopPrimaryNo = MultiPartRequestHelper.createRequestBody("shop_primary_number", newShopData.shopPrimaryNo)
        val partShopSecondaryNo = MultiPartRequestHelper.createRequestBody("shop_secondary_number", newShopData.shopSecondaryNo)
        val partShopAddress = MultiPartRequestHelper.createRequestBody("shop_address", newShopData.shopAddress)
        val partShopLat = MultiPartRequestHelper.createRequestBody("lattitude", newShopData.shopLat)
        val partShopLon = MultiPartRequestHelper.createRequestBody("longitude", newShopData.shopLon)
        val partShopEmail = MultiPartRequestHelper.createRequestBody("shopEmail", newShopData.shopEmail)
        val partShopRoute = MultiPartRequestHelper.createRequestBody("route_id", newShopData.shopRoute)
        val partShopOutstanding = MultiPartRequestHelper.createRequestBody("shop_oustanding_amount", "0")
        val partFile = MultiPartRequestHelper.createFileRequestBody(newShopData.shopImgPath, "image", context)
        val newShopMultiPartData = NewShopMultiPartData(
            partShopName,
            partShopReg,
            partShopPrimaryNo,
            partShopSecondaryNo,
            partShopAddress,
            partShopLat,
            partShopLon,
            partShopEmail,
            partShopRoute,
            partFile,
            partShopOutstanding,
            loginId,
            added_by

        )
        viewModelScope.launch {
            shopRepository.uploadNewShop(newShopMultiPartData).let {
                _addNewShop.value = it
            }
        }
    }


    fun getDeliveryShopsList(orderStatus: String){
        viewModelScope.launch {
            shopRepository.getDeliveryShopsList(orderStatus).let {
                _deliveryShopsList.value = it
            }
        }
    }


    fun getDeliveryShopsList(fromDate: String, toDate: String){
        viewModelScope.launch {
            shopRepository.getDeliveryShopsList(fromDate, toDate).let {
                _deliveryShopsList.value = it
            }
        }
    }


    fun getPendingOrders(orderStatus: String, shopId: String){
        viewModelScope.launch {
            val pendingOrderRequest = PendingOrderRequest(AppPreferences.userId, orderStatus, shopId)
            shopRepository.getPendingOrderList(pendingOrderRequest).let {
                _pendingOrderList.value = it
            }
        }
    }

    fun getPendingOrderItems(orderId: String){
        viewModelScope.launch {
            val orderItemRequest = OrderItemRequest(orderId)
            shopRepository.getPendingOrderItemsList(orderItemRequest).let {
                _orderItemsList.value = it
            }
        }
    }

    fun submitCreatePaidAmount(shop_login_id: String, amount:String, payment_mode: String, pay_remarks: String,note: String){

        var paidRequestModel =PaidAmountRequestModel(shop_login_id,amount,payment_mode,pay_remarks,note,AppPreferences.userId)

        viewModelScope.launch {
            shopRepository.submitCreatedPaidAmount(paidRequestModel).collect {
                _paidStatus.value = it
            }
        }
    }
}