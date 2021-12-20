package com.zero.testappweather.ui.main

import com.zero.testappweather.R
import com.zero.testappweather.base.BaseActivity

class MainActivity: BaseActivity(R.layout.activity_main) {
    override fun onActivityCreated() {

    }

    override fun backPressed(): Boolean {
        return true
    }
}