package com.vivy.doctorsearch.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bailm.vivychallenge.data.NetworkState
import com.vivy.doctorfinder.data.model.Doctor
import com.vivy.doctorsearch.R
import com.vivy.doctorsearch.databinding.SearchItemBinding

class SearchAdapter : PagedListAdapter<Doctor, RecyclerView.ViewHolder>(diffCallBack) {
    var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.search_item -> SearchViewHolder.create(parent)
            R.layout.progress_bar -> LoadingViewHolder.create(parent)
            else -> throw IllegalArgumentException("IllegalArgumentException Type")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            R.layout.search_item -> {
                (holder as SearchViewHolder).bind(getItem(position)!!)
            }
            R.layout.progress_bar -> {
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasLoadingRow() && position == itemCount - 1) {
            R.layout.progress_bar
        } else {
            R.layout.search_item
        }
    }


    override fun getItemCount(): Int = super.getItemCount() + if (hasLoadingRow()) 1 else 0

    private fun hasLoadingRow(): Boolean = networkState != null && networkState == NetworkState.LOADING

    class SearchViewHolder constructor(var binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): SearchViewHolder {
                val layoutInflator = LayoutInflater.from(parent.context)
                val itemDoctorsListBinding: SearchItemBinding =
                    DataBindingUtil.inflate(layoutInflator, R.layout.search_item, parent, false)
                return SearchViewHolder(itemDoctorsListBinding)
            }
        }

        fun bind(doctor: Doctor) {
            binding.user = doctor
        }
    }
    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): LoadingViewHolder {
                val layoutInflator = LayoutInflater.from(parent.context)
                return LoadingViewHolder(layoutInflator.inflate(R.layout.progress_bar, parent, false))
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is SearchViewHolder) {
            holder.binding.textName.text = null
            holder.binding.textAddress.text = null
        }
    }

    companion object {

        private val diffCallBack = object : DiffUtil.ItemCallback<Doctor>() {
            override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean = oldItem.id == newItem.id
        }
    }

    fun updateNetwokState(newNetworkState: NetworkState) {
        val oldNetworkState = networkState;
        val hadLoadingRow = hasLoadingRow()
        this.networkState = newNetworkState;
        val hasLoadingRow = hasLoadingRow();
        if (hadLoadingRow != hasLoadingRow) {
            if (hadLoadingRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasLoadingRow && oldNetworkState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}
