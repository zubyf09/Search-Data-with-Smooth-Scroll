package com.vivy.doctorfinder.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import codenevisha.ir.mvvmwithdagger.data.db.AppDatabase
import com.vivy.doctorfinder.data.model.Doctor
import com.vivy.doctorfinder.data.model.LoginModel
import com.vivy.doctorfinder.data.model.SearchModel
import com.vivy.doctorfinder.data.network.ApiDisposable
import com.vivy.doctorfinder.data.network.ApiError
import com.vivy.doctorfinder.data.network.ApiService
import com.vivy.doctorsearch.di.qualifier.ViewModelKey
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.HashMap

class AppRepoImp(
    val apiService: ApiService,
    val database: AppDatabase

):AppRepository{


    override fun search(
        query: String,
        lat: Double,
        lng: Double,
        lastKey: String,
        accessToke: String,
        success: (SearchModel) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {

        return apiService.search(
            mapOf("search" to query, "lat" to lat, "lng" to lng,"lastKey" to lastKey),
            getHeaderWithToken(accessToke)
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<SearchModel>(
                    {
                        success(it)
                    },
                    {
                        failure(it)
                    }

                )
            )

    }

//    override fun search(
//        query: String,
//        lat: Double,
//        lng: Double,
//        lastKey: String,
//        accessToke: String,
//
//        success: (SearchModel) -> Unit,
//        failure: (ApiError) -> Unit,
//        terminate: () -> Unit
//    ): Disposable {
//        return apiService.search(
//            mapOf("search" to query, "lat" to lat, "lng" to lng),
//            getHeaderWithToken(accessToke)
//        ).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnTerminate(terminate)
//            .subscribeWith(
//                ApiDisposable<SearchModel>(
//                    {
//                        success(it)
//                    },
//                    {
//                        failure(it)
//                    }
//
//                )
//            )
//    }


    override fun login(success: (LoginModel) -> Unit,
                       failure: (ApiError) -> Unit,
                       terminate: () -> Unit):
            Disposable {


        return apiService
            .login("androidChallenge@vivy.com", "88888888", getBasicHeaders())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<LoginModel>(
                    {
                        success(it)
                    },
                    {
                        failure(it)
                    }
                )
            )
    }




    override fun insertDoctor(doc: Doctor): Disposable =
        Observable
            .fromCallable { database.searchDao().insertDoctor(doc) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "Doctor added: subscribe: $it")
            }


    private fun getBasicHeaders(): Map<String, String> {

        var headers = java.util.HashMap<String, String>()
        headers.put("Authorization", "Basic aXBob25lOmlwaG9uZXdpbGxub3RiZXRoZXJlYW55bW9yZQ==")

        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/x-www-form-urlencoded"
        return headers
    }

    private fun getHeaderWithToken(token:String ): Map<String, String> {
        var headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer $token"
//        headers["Accept"] = "application/json"
//        headers["Content-Type"] = "application/x-www-form-urlencoded"
//
        return headers
    }

}