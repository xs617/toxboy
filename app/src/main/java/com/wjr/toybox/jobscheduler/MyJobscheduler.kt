package com.wjr.toybox.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.DialogInterface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.TextView

import com.wjr.toybox.R

class MyJobscheduler : AppCompatActivity() {
    var jobId = 18
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_jobscheduler)
        var countView = findViewById(R.id.count) as TextView

        var jobInfo = JobInfo.Builder(jobId, ComponentName(this, MyJob::class.java)).build()


    }

    class MyJob : JobService() {
        override fun onStopJob(params: JobParameters?): Boolean {
            return false
        }

        override fun onStartJob(params: JobParameters?): Boolean {
            return false
        }

    }
}
