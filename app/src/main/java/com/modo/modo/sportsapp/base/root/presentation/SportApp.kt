package com.modo.modo.sportsapp.base.root.presentation

import android.app.Application
import com.modo.modo.sportsapp.user.base.di.baseModule
import com.modo.modo.sportsapp.base.events.allevents.di.allEventsModule
import com.modo.modo.sportsapp.base.events.detail.di.eventsModule
import com.modo.modo.sportsapp.base.login.di.loginModule
import com.modo.modo.sportsapp.base.events.myevents.di.myEventsModule
import com.modo.modo.sportsapp.user.feature5.di.profileModule
import com.modo.modo.sportsapp.user.qr.di.qrModule
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
                profileModule(),
                qrModule()
            )
        }
    }
}