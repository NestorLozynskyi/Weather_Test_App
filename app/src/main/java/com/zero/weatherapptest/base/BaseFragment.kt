package com.zero.weatherapptest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private val navigator: BaseNavigator = Navigator()
    abstract val viewModel: BaseViewModel?
    var navController: NavController? = null

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = onCreate(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        initialize()
        setFocus(view)
        observe()
        observeError()
    }

    abstract fun onCreate(i: LayoutInflater, c: ViewGroup?): T
    abstract fun initialize()

    private fun setFocus(view: View) {
        view.apply {
            isFocusableInTouchMode = true
            requestFocus()
        }
    }

    private fun observeError() {
        /*   viewModel.error.observe(viewLifecycleOwner, Observer {
           })*/
    }

    protected open fun observe() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigator.attach(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null //todo did need delete?
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.release()
    }

    private class Navigator(): BaseNavigator(){}
}