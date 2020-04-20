package com.anas.weatherlogger.base.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseRecyclerViewAdapter(private val itemResId: Int) :
    androidx.recyclerview.widget.RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemResId, parent, false)
        return BaseViewHolder(view)
    }
}