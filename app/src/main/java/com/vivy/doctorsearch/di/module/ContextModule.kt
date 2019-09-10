package com.vivy.doctorsearch.di.module

import android.app.Application
import android.content.Context
import codenevisha.ir.mvvmwithdagger.di.builder.ViewModelBuilder
import com.vivy.doctorsearch.di.builder.ActivityBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Module(includes = [ViewModelBuilder::class])
class ContextModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return  application
    }
}