package com.zero.weatherapptest.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.zero.weatherapptest.base.baseExtensions.exitVariant
import com.zero.weatherapptest.base.baseExtensions.finishFragment
import java.lang.Exception

abstract class BaseActivity(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        setContentView(layoutId)
        onActivityCreated()
    }

    abstract fun onActivityCreated()

    override fun onBackPressed() {
        if (!backPressed()){
            when {
                supportFragmentManager.backStackEntryCount > 0 -> finishFragment()
                supportFragmentManager.backStackEntryCount == 0 -> exitVariant()
                else -> super.onBackPressed()
            }
        }
    }

    abstract fun backPressed(): Boolean

    override fun onResume() {
        super.onResume()
    }
}