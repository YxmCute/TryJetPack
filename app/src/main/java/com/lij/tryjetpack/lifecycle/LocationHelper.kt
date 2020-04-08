package com.lij.tryjetpack.lifecycle

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LocationHelper<T : Location>(
    val context: Context,
    val lifecycle: Lifecycle,
    val s: (Boolean, T: Location) -> Unit,
    val e: (String) -> Unit
) : LifecycleObserver {
    lateinit var mListener: (String) -> Unit
    lateinit var merroeListener: (Boolean) -> Unit
    private var isEnable = false
    fun enable() {
        isEnable = true
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            //Log.i("timo", "enable")
        }

    }

    fun setListener(listener: (String) -> Unit, error: (Boolean) -> Unit) {
        this.mListener = listener
        this.merroeListener = error
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (isEnable) {
            Log.i("timo", "onCreate")
            s(true, Location(""))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        mListener.invoke("invoke onStart")
        // Log.i("timo", "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        //  Log.i("timo", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        // Log.i("timo", "onStop")
        merroeListener.invoke(false)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        // Log.i("timo", "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        // Log.i("timo", "onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {
        // Log.i("timo", "onAny")
    }
}