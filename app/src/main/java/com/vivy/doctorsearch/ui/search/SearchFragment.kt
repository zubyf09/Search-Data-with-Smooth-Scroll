package com.vivy.doctorsearch.ui.search

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vivy.doctorsearch.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bailm.vivychallenge.data.NetworkState
import com.vivy.doctorfinder.ui.viewmodel.SearchViewModel
import com.vivy.doctorsearch.core.Config
import com.vivy.doctorsearch.databinding.FragmentSearchBinding
import com.vivy.doctorsearch.utils.DebouncingQueryTextListener

class SearchFragment : DaggerFragment() {

    val TAG: String = SearchFragment::class.java.simpleName

    companion object{
        val FRAGMENT_NAME: String = SearchFragment::class.java.name
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var searchQuery =""
    private val viewModel: SearchViewModel by lazy { ViewModelProviders.
        of(this,viewModelFactory).get(SearchViewModel::class.java) }
    val adapter= SearchAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentSearchBinding = inflate(inflater, R.layout.fragment_search, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val location = arguments?.getParcelable<Location>(Config.CURRENT_LOCATION)
        initView()
        location?.let { observeSearchDataChanged(it) }
    }

    private fun initView() {
        search_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        search_rv.adapter = adapter
        initLoadingState()


    }

    private fun initLoadingState() {
        viewModel.loadingState.observe(this, Observer {
            if (it == NetworkState.LOADED || it == NetworkState.LOADING) {
                noDataText.visibility = View.GONE
            } else if (it == NetworkState.NO_DATA_AVAILABLE) {
                noDataText.visibility = View.VISIBLE
            }
            adapter.updateNetwokState(it)
        })
    }
    fun observeSearchDataChanged(location: Location){
        searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                lifecycle
            ){
                searchText->
                searchText?.let {
                    if (it.isEmpty()) {
                        viewModel.compositeDisposable.clear()
                    } else {
                        searchQuery = it
                        viewModel.searchData(it,location.latitude, location.longitude)
                        viewModel.dataList.observe(this, Observer {
                            adapter.submitList(it)
                        })
                    }
                }
            }
        )
    }


}


