package com.espy.onecallsys.app

import com.espy.onecallsys.api.HttpEndPoints
import com.espy.onecallsys.api.header.NetworkRequestHeader

internal class AppSettings {

    companion object{
        const val APP_PREF = "com.espy.onecallsys"

        const val CACHED_DB_PATH = "database/onecall_config_db.db"
        const val DB_NAME = "onecallapp.db"

        const val NETWORK_READ_TIME_OUT = 30 * 1000
        const val NETWORK_CONNECTION_TIME_OUT = 10 * 1000

        const val APP_FILE_PROVIDER = "fileprovider"

        val endPoints = HttpEndPoints

        val cacheControl = NetworkRequestHeader("Cache-Control", "no-cache")
        val contentType = NetworkRequestHeader("Content-Type", "application/json")
    }
}