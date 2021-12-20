package com.zero.weatherapptest.base.baseExtensions

import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.zero.weatherapptest.R
import com.zero.weatherapptest.base.BaseFragment

fun FragmentActivity.finishFragment() {
    supportFragmentManager.popBackStack()
}

var exit = false
fun FragmentActivity.exitVariant() {
    if (exit) {
        finishAffinity()
    } else {
        Toast.makeText(this, this.getString(R.string.back_again), Toast.LENGTH_SHORT).show()
        exit = true
        Handler().postDelayed({ exit = false }, 2000)
    }
}

/*fun FragmentActivity.initialFragment(fragment: Fragment) {
    val containerId = ViewModelProviders.of(this)[BaseViewModel::class.java].parentLayoutId
    supportFragmentManager.commit(allowStateLoss = true) {
        replace(containerId, fragment)
    }
}*/

/*fun BaseFragment.parentLayoutId() =
    androidx.lifecycle.ViewModelProviders.of(activity!!)[BaseViewModel::class.java].parentLayoutId

fun BaseFragment.navLayoutId() =
    androidx.lifecycle.ViewModelProviders.of(activity!!)[BaseViewModel::class.java].navLayoutId*/