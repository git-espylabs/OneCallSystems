package com.espy.onecallsys.api.models

import androidx.annotation.Keep
import com.espy.onecallsys.app.EspyAppModel
import com.google.gson.annotations.SerializedName

@Keep
open class ResponseBase(
    var isSuccess: Boolean = false,
    @SerializedName("error")
    var isError: Boolean = false,
    @SerializedName("message")
    var description: Any? = ""
) : EspyAppModel {

    fun isValid(): Boolean {
        return isError
    }

    fun getMessage(): String {
        return if (description is String) {
            description as String
        } else {
            ""
        }
    }

    companion object {
        private const val API_FAILED = -1
        private const val API_SUCCESS = 1
    }
}