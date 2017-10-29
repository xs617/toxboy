package com.wjr.toybox.databinding

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by wen on 2017/9/21.
 */
class DataBindingViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    lateinit var dataBinding : ItemDataBindingBinding
    fun  getBinding() : ItemDataBindingBinding{
        return dataBinding
    }
    fun  setBinding(dataBinding : ItemDataBindingBinding){
        this.dataBinding = dataBinding
    }
}