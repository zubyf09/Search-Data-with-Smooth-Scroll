package com.vivy.doctorfinder.ui.splash

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vivy.doctorsearch.MainActivity
import com.vivy.doctorsearch.R
import com.vivy.doctorsearch.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Named

class SplashActivity : BaseActivity(){


    override fun getLayoutById(): Int = R.layout.splash_screen

    override fun onLocationAvailable(location: Location) {
        location?.let {
            startActivity(MainActivity.getIntent(this))
            finish()
        }
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: SplashViewModel


    override fun initUI() {
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(SplashViewModel::class.java)
        checkAccessToken()
    }
    fun checkAccessToken(){

        viewModel.accessToken.observe(
            this, Observer {
                t ->
                if(t!=null){
                }
            }
        )
    }




}