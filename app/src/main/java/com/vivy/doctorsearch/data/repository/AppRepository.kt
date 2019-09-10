package com.vivy.doctorfinder.data.repository

import com.vivy.doctorfinder.data.model.Doctor
import com.vivy.doctorfinder.data.model.LoginModel
import com.vivy.doctorfinder.data.model.SearchModel
import com.vivy.doctorfinder.data.network.ApiError
import dagger.Provides
import io.reactivex.disposables.Disposable


interface AppRepository {


    companion object {
        var token: String = ""
    }

    fun search(
        query: String,
        lat: Double,
        lng: Double,
        lastKey: String,
        accessToke: String,
        success: (SearchModel) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable


    fun login(
        success: (LoginModel) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable


    fun insertDoctor(doc: Doctor): Disposable



}