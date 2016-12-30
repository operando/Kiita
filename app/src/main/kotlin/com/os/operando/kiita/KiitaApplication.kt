package com.os.operando.kiita

import android.app.Application
import com.os.operando.kiita.di.component.AppComponent
import com.os.operando.kiita.di.component.DaggerAppComponent

class KiitaApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}