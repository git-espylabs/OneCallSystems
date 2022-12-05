package com.espy.onecallsys.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.espy.onecallsys.R
import com.espy.onecallsys.extensions.launchActivity
import com.espy.onecallsys.preference.AppPreferences
import com.espy.onecallsys.ui.home.view.HomeActivity
import com.espy.onecallsys.ui.profile.view.LoginActivity


class SplashActivity : AppCompatActivity() {

    private val DELAY = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (AppPreferences.userId.isNotEmpty()) {
                launchActivity<HomeActivity>()
                finish()
            } else {
                launchActivity<LoginActivity>()
                finish()
            }
        }, DELAY)

    }
}