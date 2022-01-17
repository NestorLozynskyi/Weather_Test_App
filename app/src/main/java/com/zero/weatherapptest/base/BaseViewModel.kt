package com.zero.weatherapptest.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zero.weatherapptest.utils.coroutine.CoroutineHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    var showError: MutableLiveData<String> = MutableLiveData()

    private var coroutineHelper = CoroutineHelper(viewModelScope)

    protected open fun launch(
        onError: (e: Throwable) -> Unit,
        coroutineContext: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return coroutineHelper.launch(coroutineContext, block, onError)
    }

    open fun onErrorHandler(throwable: Throwable) {

    }
}