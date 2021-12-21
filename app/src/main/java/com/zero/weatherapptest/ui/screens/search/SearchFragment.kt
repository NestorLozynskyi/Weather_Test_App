package com.zero.weatherapptest.ui.screens.search

import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment
import com.zero.weatherapptest.base.BaseViewModel
import com.zero.weatherapptest.ui.screens.profile.ProfileViewModel
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment() : BaseFragment(R.layout.screen_search) {
    override val navigator: SearchNavigation = get()
    override val viewModel: SearchViewModel by viewModel()

    override fun initialize() {

    }

}