package com.loki.moepoc

import android.app.Application
import com.loki.moepoc.di.AppComponent
import com.loki.core.di.AppModule
import com.loki.moepoc.di.DaggerAppComponent
import kotlin.properties.Delegates

class PocApp : Application() {

    var appComponent: AppComponent by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}
