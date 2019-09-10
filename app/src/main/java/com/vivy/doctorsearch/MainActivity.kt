package com.vivy.doctorsearch

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import com.vivy.doctorsearch.core.Config
import com.vivy.doctorsearch.ui.base.BaseActivity
import com.vivy.doctorsearch.ui.search.SearchFragment

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val searchFragment = SearchFragment()
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
    override fun onLocationAvailable(location: Location) {
        showHomeFragment(location)
    }

    override fun getLayoutById() = R.layout.activity_main
    override fun initUI() {
    }

    private fun showHomeFragment(location: Location) {

        val bundle = Bundle()
        bundle.putParcelable(Config.CURRENT_LOCATION, location)
        searchFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.container, searchFragment).commit()
    }
}
