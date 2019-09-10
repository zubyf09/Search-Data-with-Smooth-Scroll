package com.vivy.doctorsearch.di.builder

import com.vivy.doctorfinder.ui.splash.SplashActivity
import com.vivy.doctorsearch.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity


}
