package com.zero.weatherapptest.base

import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

abstract class BaseNavigator {

    var activity: WeakReference<FragmentActivity?> = WeakReference(null)

    fun attach(activity: FragmentActivity) {
        this.activity = WeakReference(activity)
    }

    fun release() {
        this.activity = WeakReference(null)
    }
}