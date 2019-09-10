package com.vivy.doctorfinder.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vivy.doctorfinder.data.model.Doctor
import com.vivy.doctorfinder.data.repository.AppRepository
import mvvm.sample.foods.ui.base.BaseViewModel
import javax.inject.Inject
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vivy.doctorsearch.ui.paging.SearchSourceFactory

class SearchViewModel@Inject constructor( appRepository: AppRepository) : BaseViewModel() {
    @Inject
    lateinit var appRepository: AppRepository
    var searchKey = ""
    var dataList: LiveData<PagedList<Doctor>> = MutableLiveData<PagedList<Doctor>>()
    var searchSourceFactory: SearchSourceFactory = SearchSourceFactory();

    var loadingState =
        Transformations.switchMap(searchSourceFactory.sourceData, { it.networkState })

    var latitude: Double = 0.0
    var longitude: Double = 0.0

    fun searchData(query:String, lat: Double, lng: Double) {
        this.latitude = lat
        this.longitude = lng
        searchSourceFactory.setParams(appRepository, query, lat, lng)

        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()
        dataList = LivePagedListBuilder(searchSourceFactory, pagedListConfig)
            .build()

    }


}