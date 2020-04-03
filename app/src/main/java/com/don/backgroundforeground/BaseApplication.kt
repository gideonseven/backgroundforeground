package com.don.backgroundforeground

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.util.Log
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

/**
 *
 * Based On article from medium
 * https://medium.com/@mobile_develop/android-application-development-detecting-when-your-app-enters-the-background-or-foreground-bbced47ad8a5
 */

class BaseApplication : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        const val INTERVAL_BACKGROUND_STATE_CHANGE = 750L
    }


    private var currentActivityReference: WeakReference<Activity>? = null
    private val isInBackground: AtomicBoolean = AtomicBoolean(true)

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }

    private fun determineForegroundStatus() {
        if (isInBackground.get()) {
            onEnterForeground()
            isInBackground.set(false)
        }
    }

    private fun determineBackgroundStatus() {
        Handler().postDelayed({
            if (!isInBackground.get() && currentActivityReference == null) {
                isInBackground.set(true)
                onEnterBackground()
            }
        }, INTERVAL_BACKGROUND_STATE_CHANGE)
    }

    private fun onEnterForeground() {
        Log.e("STATUS", "=== ON ENTER FOREGROUND")
    }

    private fun onEnterBackground() {
        Log.e("STATUS", "=== ON ENTER BACKGROUND")
        MySharedPreference.clearPreference(this)
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivityReference = WeakReference(activity)
        determineForegroundStatus()
        Log.e("STATUS", "=== ON ACTIVITY RESUME")
    }

    override fun onActivityPaused(activity: Activity) {
        currentActivityReference = null
        determineBackgroundStatus()
        Log.e("STATUS", "=== ON ACTIVITY PAUSED")
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

}