package com.vivy.doctorsearch.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.vivy.doctorfinder.data.model.Doctor
import com.vivy.doctorfinder.data.repository.AppRepository

class SearchSourceFactory : DataSource.Factory<String, Doctor>() {
    lateinit var appRepository: AppRepository
    lateinit var searchKey: String
    var lat: Double = 0.0
    var lng: Double = 0.0
    val sourceData = MutableLiveData<SearchDataSource>();
    lateinit var source: SearchDataSource
    override fun create(): DataSource<String, Doctor> {
        source = SearchDataSource(appRepository, lat, lng, searchKey)
        sourceData.postValue(source)
        return source
    }

    fun setParams(doctorsRepository: AppRepository, searchKey: String, lat: Double , lng: Double) {
        this.lat = lat
        this.lng = lng
        this.appRepository = doctorsRepository
        this.searchKey = searchKey

    }


}
