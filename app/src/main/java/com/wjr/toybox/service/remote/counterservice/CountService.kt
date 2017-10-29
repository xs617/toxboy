package com.wjr.toybox.service.remote.counterservice

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

/**
 * Created by wen on 2017/9/22.
 */
class CountService : Service() {
    var isStop = true
    var counter = Count(0, 1000)
    var usingCount = 0
    lateinit var countThread: Thread
    var binder: IBinder = object : ICounter.Stub() {
        override fun setDuration(duration: Int) {
            counter.duration = duration
        }

        override fun getCurrentCount(): Count {
            if (isStop) {
                isStop = false
                countThread = Thread(object : Runnable {
                    override fun run() {
                        while (!isStop) {
                            counter.count++
                            Thread.sleep(counter.duration.toLong())
                        }
                    }
                })
                countThread.start()
            }
            return counter
        }

        override fun stopCount() {
            usingCount--
            if (usingCount <= 0) {
                isStop = true
            }
        }

        override fun reset() {
            counter.count = 0
        }

    }

    override fun onBind(intent: Intent?): IBinder {
        usingCount++
        return binder
    }

    override fun unbindService(conn: ServiceConnection?) {
        super.unbindService(conn)
        isStop = true
    }

}