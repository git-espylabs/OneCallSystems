package com.espy.onecallsys.api.factory

import com.espy.onecallsys.api.Environment
import com.espy.onecallsys.api.interceptor.NetworkRequestInterceptor
import com.espy.onecallsys.api.interceptor.UnauthorisedInterceptor
import com.espy.onecallsys.app.AppSettings
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter

class NetworkServiceFactory(
    converterFactory: Converter.Factory,
    callAdapterFactory: CallAdapter.Factory,
    logLevel: HttpLoggingInterceptor.Level,
    environment: Environment,
) : ServiceFactory(converterFactory, callAdapterFactory, logLevel, environment) {

    override fun interceptors(): List<Interceptor> {
        return arrayListOf(
            NetworkRequestInterceptor(
                listOf(
                    AppSettings.cacheControl,
                    AppSettings.contentType
                )
            ),
            UnauthorisedInterceptor()
        )
    }
}