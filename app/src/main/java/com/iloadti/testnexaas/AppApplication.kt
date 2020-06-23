package com.iloadti.testnexaas

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.iloadti.testnexaas.di.AppModule

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule().init(this)
        Fresco.initialize(this)
    }


}