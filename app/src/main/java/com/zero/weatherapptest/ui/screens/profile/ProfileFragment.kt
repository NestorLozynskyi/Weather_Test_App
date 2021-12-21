package com.zero.weatherapptest.ui.screens.profile

import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment
import com.zero.weatherapptest.databinding.ScreenProfileBinding
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(R.layout.screen_profile) {

    override val navigator: ProfileNavigation = get()
    override val viewModel: ProfileViewModel by viewModel()
    private lateinit var viewBinding: ScreenProfileBinding

    override fun initialize() {
        viewBinding.find.setOnClickListener {
            viewModel.getProfile()
        }
    }


}