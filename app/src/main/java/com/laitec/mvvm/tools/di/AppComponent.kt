package com.laitec.mvvm.tools.di

import com.laitec.mvvm.ui.activity.MainActivity
import dagger.Component


@Component(
    modules = arrayOf(AppModule::class)
)
interface AppComponent {
    fun inject(target : MainActivity)
}