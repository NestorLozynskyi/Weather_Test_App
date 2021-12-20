package com.zero.weatherapptest.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes val layoutId: Int) : Fragment(layoutId) {

    abstract val navigator: BaseNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
        setFocus(view)
        observe()
        observeError()
    }

    abstract fun initialize()

    private fun setFocus(view: View) {
        view.apply {
            isFocusableInTouchMode = true
            requestFocus()
            /*setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (enableCustomBackPress) onFragmentBackButtonPressed()
                    else activity?.onBackPressed()
                }
                enableCustomBackPress = false
                true
            }*/
        }
    }

    private fun observeError() {
        /*   viewModel.error.observe(viewLifecycleOwner, Observer {
           })*/
    }

    protected open fun observe() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator.attach(requireActivity())
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.release()
    }
}