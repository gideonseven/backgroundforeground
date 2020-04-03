package com.don.backgroundforeground

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

/**
 *
 * Based On article from medium
 * https://medium.com/@mobile_develop/android-application-development-detecting-when-your-app-enters-the-foreground-or-background-with-a806fbe68c42
 */

class BaseApplication : Application(), LifecycleObserver{

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onEnterForeground() {
        Log.e("STATUS", "=== ON ENTER FOREGROUND")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onEnterBackground() {
        Log.e("STATUS", "=== ON ENTER BACKGROUND")
        MySharedPreference.clearPreference(this)
    }


}