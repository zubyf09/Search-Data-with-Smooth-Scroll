package com.vivy.doctorsearch.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.bailm.vivychallenge.data.NetworkState
import com.vivy.doctorfinder.data.model.Doctor
import com.vivy.doctorfinder.data.repository.AppRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchDataSource(
    var appRepository: AppRepository,
    var lat: Double,
    var lng: Double,
    var searchKey: String
) : ItemKeyedDataSource<String, Doctor>() {
    var networkState = MutableLiveData<NetworkState>()
    var lastKey: String = ""
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Doctor>) {
        networkState.postValue(NetworkState.LOADING)
        loadData(searchKey, lat, lng, lastKey) { doctors ->
            callback.onResult(doctors)
            if (doctors.isEmpty()) {
                networkState.postValue(NetworkState.NO_DATA_AVAILABLE)
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Doctor>) {
        networkState.postValue(NetworkState.LOADING)
        loadData(searchKey, lat, lng, params.key) { doctors ->
            callback.onResult(doctors)

        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Doctor>) {

    }

    override fun getKey(item: Doctor): String {
        return lastKey
    }

    private fun loadData(
        searchKey: String,
        lat: Double,
        lng: Double,
        lastKey: String,
        //a func to deal with the data after getting it from the pai
        respondingFunc: (List<Doctor>) -> Unit
    ) {

        appRepository.search(searchKey, lat, lng, lastKey,accessToke = AppRepository.token,
            success = {
                searchModel ->
                networkState.postValue(NetworkState.LOADED)
                    respondingFunc.invoke(searchModel.items)
                    if (searchModel.lastKey == null)
                        this@SearchDataSource.lastKey = INVALID_KEY
                     else
                        this@SearchDataSource.lastKey = searchModel.lastKey


            },
            failure = {
            })
    }


    override fun isInvalid(): Boolean {
        return lastKey == INVALID_KEY
    }


    companion object {
        const val INVALID_KEY = "INVALID_KEY"
    }

    override fun invalidate() {
        lastKey = ""
        super.invalidate()
    }
}