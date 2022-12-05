package com.espy.onecallsys.location

import android.location.Location

interface GpsListener {
    fun onLocationUpdate(location: Location?)
    fun onLocationDisabled()
}