package com.zero.weatherapptest.ui.screens.search

import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment
import org.koin.android.ext.android.get

class SearchFragment : BaseFragment(R.layout.screen_search) {
    override val navigator: SearchNavigation = get()

    override fun initialize() {

    }

}