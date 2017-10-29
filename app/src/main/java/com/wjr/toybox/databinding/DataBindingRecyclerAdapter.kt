package com.wjr.toybox.databinding

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wjr.toybox.R

/**
 * Created by wen on 2017/9/21.
 */
class DataBindingRecyclerAdapter(var context: Context, var data: ArrayList<User>) : RecyclerView.Adapter<DataBindingViewHolder>() {
    override fun onBindViewHolder(holder: DataBindingViewHolder?, position: Int) {

        holder?.dataBinding?.user = data.get(position)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBindingViewHolder {
        var databinding = DataBindingUtil.inflate<ItemDataBindingBinding>(LayoutInflater.from(context), R.layout.item_data_binding, parent, false)
        var dataBindingViewHolder = DataBindingViewHolder(databinding.root)
        dataBindingViewHolder.setBinding(databinding)
        return dataBindingViewHolder
    }
}