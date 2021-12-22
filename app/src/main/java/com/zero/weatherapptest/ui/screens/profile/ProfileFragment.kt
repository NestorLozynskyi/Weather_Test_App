package com.zero.weatherapptest.ui.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zero.weatherapptest.base.BaseFragment
import com.zero.weatherapptest.databinding.ScreenProfileBinding
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    override val navigator: ProfileNavigation = get()
    override val viewModel: ProfileViewModel by viewModel()
    //private var viewBinding = ScreenProfileBinding.inflate(layoutInflater)//ScreenProfileBinding.inflate(layoutInflater)

    override fun initialize() {

        /*val view = viewBinding.root
        setContentView(view)*/

        viewBinding?.find?.setOnClickListener {
            viewModel.getProfile()
        }
    }

    private var viewBinding: ScreenProfileBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = ScreenProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}