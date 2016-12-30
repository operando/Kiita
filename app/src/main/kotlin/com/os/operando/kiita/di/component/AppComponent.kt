package com.os.operando.kiita.di.component

import com.os.operando.kiita.activity.MainActivity
import com.os.operando.kiita.di.module.ClientModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ClientModule::class))
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}