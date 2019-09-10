package codenevisha.ir.mvvmwithdagger.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vivy.doctorfinder.ui.splash.SplashViewModel
import com.vivy.doctorsearch.di.builder.AppViewModelBuilder
import com.vivy.doctorsearch.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    (AppViewModelBuilder::class)
])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory



}