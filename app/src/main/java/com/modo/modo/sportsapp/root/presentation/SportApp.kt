package com.modo.modo.sportsapp.root.presentation

import android.app.Application
import com.modo.modo.sportsapp._temp.activity.di.activityModule
import com.modo.modo.sportsapp._temp.qr.di.qrModule
import com.modo.modo.sportsapp._temp.user.di.userModule
import com.modo.modo.sportsapp.base.di.baseModule
import com.modo.modo.sportsapp.events.di.eventsModule
import com.modo.modo.sportsapp.login.di.loginModule
import com.modo.modo.sportsapp.myevents.di.myEventsModule
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
                eventsModule(),
                myEventsModule(),
                qrModule(),
                activityModule(),
                userModule()
            )
        }
    }
}