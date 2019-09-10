package com.vivy.doctorsearch.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GenericAdapter<T>(
    val layout: Int,
    val list: MutableList<T>,
    val action: (
        viewHolder: GenericAdapter.ViewHolder,
        view: View,
        entry: T,
        position: Int) -> Unit) : RecyclerView.Adapter<GenericAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        action.invoke(holder, holder.view, list[position], position)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
    }

}