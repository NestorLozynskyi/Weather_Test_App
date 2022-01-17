package com.zero.weatherapptest.ui.main

import androidx.navigation.Navigation
import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseActivity
import java.lang.Exception

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onActivityCreated() {}

    override fun backPressed(): Boolean {
        return false
    }

    override fun onStart() {
        super.onStart()
        try {
            Navigation.findNavController(this, R.id.navHostFragment)
                .navigate(R.id.onCreate)

        } catch (e: Exception) {
            println(e)
        }
    }
}
