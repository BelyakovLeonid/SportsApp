package com.modo.modo.sportsapp.root.presentation

import android.app.Application
import com.modo.modo.sportsapp.base.di.baseModule
import com.modo.modo.sportsapp.events.allevents.di.allEventsModule
import com.modo.modo.sportsapp.events.detail.di.eventsModule
import com.modo.modo.sportsapp.login.di.loginModule
import com.modo.modo.sportsapp.events.myevents.di.myEventsModule
import com.modo.modo.sportsapp.qr.di.qrModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SportApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SportApp)
            modules(
                baseModule(),
                loginModule(),
                myEventsModule(),
                eventsModule(),
                allEventsModule(),
                qrModule()
            )
        }
    }
}