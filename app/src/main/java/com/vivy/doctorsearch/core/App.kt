package com.vivy.doctorsearch.core

import android.content.Context
import androidx.multidex.MultiDex
import com.vivy.doctorsearch.di.component.DaggerCoreComponent
import dagger.Binds
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class  App : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoreComponent
            .builder()
            .application(this)
            .build()


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}