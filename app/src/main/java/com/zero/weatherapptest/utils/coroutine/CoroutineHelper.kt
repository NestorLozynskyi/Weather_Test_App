package com.zero.weatherapptest.utils.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineHelper(private val scope: CoroutineScope) {

    fun launch(coroutineContext: CoroutineContext = Dispatchers.IO,
               block: suspend CoroutineScope.() -> Unit,
               onError: (e: Throwable) -> Unit
    )  = scope.launch(coroutineContext + ExceptionHandler(onError)) {

        block()
    }

    inner class ExceptionHandler(private val onError : (Throwable) -> Unit) :
        CoroutineExceptionHandler {
        override val key: CoroutineContext.Key<*>
            get() = CoroutineExceptionHandler.Key

        override fun handleException(context: CoroutineContext, exception: Throwable) {
            scope.launch(Dispatchers.Main) { onError(exception) }
        }
    }


    /*fun startTimer(timer: Int, block: suspend CoroutineScope.(timerResponse: TimerResult) -> Unit) {
        scope.launch {
            repeat(timer + 1) {
                block(TimerResult(timer, timer - it, it >= timer))
                delay(1_000)
            }
        }
    }*/
}