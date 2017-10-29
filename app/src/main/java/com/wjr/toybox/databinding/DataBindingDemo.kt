package com.wjr.toybox.databinding

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.wjr.toybox.R

class DataBindingDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding :ActivityDataBindingDemoBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding_demo)
        var user = User(ObservableField("uuuser"),ObservableField(18))
        binding.outerUser = user

        var dataArray = ArrayList<User>()
        var i = 0
        do{
            dataArray.add(User(ObservableField("user " + i),ObservableField(i)))
            i++
        }while (i<10)

        var recyclerView =  findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.adapter = DataBindingRecyclerAdapter(this,dataArray)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
}
