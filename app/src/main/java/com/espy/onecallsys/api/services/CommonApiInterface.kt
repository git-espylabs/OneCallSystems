package com.espy.onecallsys.api.services

import com.espy.onecallsys.api.HttpEndPoints
import com.espy.onecallsys.api.models.ResponseBase
import com.espy.onecallsys.api.models.TempResponse
import com.espy.onecallsys.api.models.common.ExpenseTypes
import com.espy.onecallsys.api.models.common.PaymentTypes
import com.espy.onecallsys.api.models.login.*
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import retrofit2.http.*

interface CommonApiInterface {

    @POST(HttpEndPoints.ONECALL_LOGIN)
    fun loginUserAsync(
        @Body loginRequest: LoginRequest
    ): Deferred<LoginResponse>

    @GET(HttpEndPoints.ONECALL_PAY_TYPES)
    fun getPaymentTypesAsync(
    ): Deferred<PaymentTypes>

    @POST(HttpEndPoints.ONECALL_PROFILE)
    fun getProfileAsync(
        @Body profileRequest: ProfileRequest
    ): Deferred<ProfileResponse>

    @POST(HttpEndPoints.ONECALL_APPLY_LEAVE)
    fun applyLeaveAsync(
        @Body leaveRequest: LeaveRequest
    ): Deferred<ResponseBase>

    @POST(HttpEndPoints.ONECALL_EXPENSE_TYPES)
    fun getExpenseTypesAsync(
    ): Deferred<ExpenseTypes>

    @Multipart
    @POST(HttpEndPoints.ONECALL_ADD_EXPENSE)
    fun submitExpenseAsync(
        @Part type_id: MultipartBody.Part,
        @Part amount: MultipartBody.Part,
        @Part note: MultipartBody.Part,
        @Part expense_type: MultipartBody.Part,
        @Part added_by: MultipartBody.Part,
        @Part image: MultipartBody.Part
    ): Deferred<TempResponse>
}