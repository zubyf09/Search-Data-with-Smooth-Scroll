package com.vivy.doctorsearch.di.builder

import com.vivy.doctorfinder.ui.splash.SplashActivity
import com.vivy.doctorsearch.MainActivity
import com.vivy.doctorsearch.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class ActivityProviders {

//    @ContributesAndroidInjector
//    abstract fun provideSplashActivity(): SplashActivity


    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment



}