package org.bmsk.deliciouscompass

import android.app.Application
import android.content.Context

class DeliciousCompassApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DeliciousCompassApplication.applicationContext = applicationContext
    }

    companion object {
        lateinit var applicationContext: Context
    }
}