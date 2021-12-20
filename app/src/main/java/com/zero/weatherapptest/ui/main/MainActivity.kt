package com.zero.weatherapptest.ui.main

import androidx.navigation.Navigation
import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onActivityCreated() {
        startFragment()
    }

    override fun backPressed(): Boolean {
        return true
    }

    private fun startFragment() {
        //initialFragment(Splash())
        Navigation.findNavController(this, R.id.navHostFragment)
            .navigate(R.id.action_pin_code)
    }
}
