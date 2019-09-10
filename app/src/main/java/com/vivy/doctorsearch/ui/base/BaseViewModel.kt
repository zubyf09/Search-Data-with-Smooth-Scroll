package mvvm.sample.foods.ui.base


import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel
    : ViewModel(), LifecycleObserver {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
