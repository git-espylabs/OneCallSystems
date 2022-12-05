package com.espy.onecallsys.ui.profile.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.espy.onecallsys.R
import com.espy.onecallsys.databinding.ActivityLoginBinding
import com.espy.onecallsys.ui.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login,
    false,
    R.string.app_name
) {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding?.apply {
            lifecycleOwner = this@LoginActivity
            this.viewModel = viewModel
        }
    }

    override fun onCreateToolbar(): Toolbar? {
        return null
    }

    override fun onCreateLoader(): View? {
        return binding?.loadingView?.loaderView
    }
}