package com.vivy.doctorsearch.di.builder


import androidx.lifecycle.ViewModel
import com.vivy.doctorfinder.ui.splash.SplashViewModel
import com.vivy.doctorfinder.ui.viewmodel.SearchViewModel
import com.vivy.doctorsearch.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

}