package com.espy.onecallsys.ui.profile.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.espy.onecallsys.R
import com.espy.onecallsys.databinding.ActivityProfileBinding
import com.espy.onecallsys.ui.base.BaseActivity

class ProfileActivity : BaseActivity<ActivityProfileBinding>(
    R.layout.activity_profile,
    true
) {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding?.apply {
            lifecycleOwner = this@ProfileActivity
            this.viewModel = viewModel
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    override fun onCreateToolbar(): Toolbar? {
        return binding?.toolbarHolder?.toolbar
    }

    override fun onCreateLoader(): View? {
        return binding?.loadingView?.loaderView
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}