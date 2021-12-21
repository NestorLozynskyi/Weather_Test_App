package com.zero.weatherapptest.ui.screens.profile

import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment
import org.koin.android.ext.android.get

class ProfileFragment : BaseFragment(R.layout.screen_profile) {

    override val navigator: ProfileNavigation = get()

    override fun initialize() {

    }
}