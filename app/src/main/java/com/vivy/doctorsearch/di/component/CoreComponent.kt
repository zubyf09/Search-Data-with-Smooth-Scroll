package com.vivy.doctorsearch.di.component

import android.app.Application
import codenevisha.ir.mvvmwithdagger.di.builder.ViewModelBuilder
import codenevisha.ir.mvvmwithdagger.di.builder.ViewModelFactory
import codenevisha.ir.mvvmwithdagger.di.module.DatabaseModule
import com.vivy.doctorsearch.core.App
import com.vivy.doctorsearch.di.builder.ActivityBuilder
import com.vivy.doctorsearch.di.module.ContextModule
import com.vivy.doctorsearch.di.module.NetworkModule
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    NetworkModule::class,
    DatabaseModule::class,
    ContextModule::class]
)
interface CoreComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app: Application):Builder

        fun build(): CoreComponent

    }



}
