package com.vivy.doctorfinder.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vivy.doctorfinder.data.model.LoginModel
import com.vivy.doctorfinder.data.repository.AppRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(appRepository: AppRepository):ViewModel(){

    private val TAG = SplashViewModel::class.java.simpleName

    var accessToken: MutableLiveData<String> = MutableLiveData()

    init {
        appRepository.login({loginModel ->
            accessToken.value = loginModel.accessToken
            AppRepository.token = accessToken.value.toString()
            Log.e("AccessToke ","Print value "+accessToken.value)
            }
        )
    }

}