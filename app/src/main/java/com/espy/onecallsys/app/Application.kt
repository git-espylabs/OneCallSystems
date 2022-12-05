package com.espy.onecallsys.app

import android.app.Application
import android.content.Context
import com.espy.onecallsys.app.AppSettings.Companion.APP_PREF
import com.espy.onecallsys.db.DatabaseProvider
import com.espy.onecallsys.preference.PreferenceProvider

private lateinit var appContext: Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        DatabaseProvider().initDb(appContext)
        PreferenceProvider.init(appContext, APP_PREF)
    }
}