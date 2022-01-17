package com.zero.weatherapptest.ui.main

import androidx.navigation.Navigation
import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseActivity
import java.lang.Exception

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onActivityCreated() {
       // startFragment()
    }

    override fun backPressed(): Boolean {
        return false
    }

    private fun startFragment() {
        //todo
        /*val bottomBar = findViewById<View>(R.id.bottom_bar)
        bottomBar.button_search_fragment.setOnClickListener {
            findNavController(R.id.action_to_SearchFragment)
        }
        bottomBar.button_profile_fragment.setOnClickListener {
            findNavController(R.id.action_to_ProfileFragment_fromLeft)
        }
        bottomBar.button_statistic_fragment.setOnClickListener {
            findNavController().navigate(R.id.action_to_StatisticFragment)
        }*/


        //initialFragment(Splash())

        /*  Navigation.findNavController(this, R.id.navHostFragment)
              .navigate(R.id.onCreate)*/
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

    override fun onPause() {
        super.onPause()
    }
}
