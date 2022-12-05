package com.espy.onecallsys.api.interceptor

import okhttp3.Interceptor

interface Interceptor {

    /**
     * Get interceptors
     */
    fun interceptors(): List<Interceptor>
}