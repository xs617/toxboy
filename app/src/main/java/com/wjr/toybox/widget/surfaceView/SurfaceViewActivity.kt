package com.wjr.toybox.widget.surfaceView

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.*

import com.wjr.toybox.R
import java.io.File
import java.io.FileInputStream

class SurfaceViewActivity : AppCompatActivity() {
    lateinit var surfaceView: SurfaceView
    lateinit var surfaceViewLayout: FrameLayout
    lateinit var videoPlayer: MediaPlayer
    lateinit var seekbar: SeekBar
    lateinit var controller: ToggleButton
    val requestCode = 1
    var curPosition = 0
    var isResume = true
    var dir = Environment.getExternalStorageDirectory().path + File.separator + "testMedia" + File.separator + "shearMoon.mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface_view)
        surfaceView = findViewById(R.id.video) as SurfaceView
        surfaceViewLayout = findViewById(R.id.layout) as FrameLayout
        controller = findViewById(R.id.controler) as ToggleButton
        controller.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isResume = false
                pausePlay()
            } else {
                isResume = true
                resumePlay()
            }
        })
        seekbar = findViewById(R.id.seek_bar) as SeekBar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser && seekBar != null) {
                    videoPlayer.seekTo((progress * 1.0 / seekBar.max * videoPlayer.duration).toInt())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var isGetAll = true
        if (requestCode == this.requestCode) {
            var i = 0
            while (i < permissions.size) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isGetAll = false
                    break
                }
                i++
            }
            if (isGetAll) {
                startPlay()
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            var layoutParam = surfaceViewLayout.layoutParams
            layoutParam.height = 400
            layoutParam.width = ViewGroup.LayoutParams.MATCH_PARENT
            surfaceViewLayout.layoutParams = layoutParam
        }
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            var layoutParam = surfaceViewLayout.layoutParams
            layoutParam.height = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParam.width = ViewGroup.LayoutParams.MATCH_PARENT
            surfaceViewLayout.layoutParams = layoutParam
        }
    }

    override fun onPause() {
        super.onPause()
        curPosition = videoPlayer.currentPosition
        isResume = videoPlayer.isPlaying
        stopPlay()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer = MediaPlayer()
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                videoPlayer.setDisplay(holder)
            }

            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
            }

        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || checkCallingOrSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode)
            } else {
                startPlay()
            }
        } else {
            startPlay()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            curPosition = savedInstanceState.getInt("position")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("position", curPosition)
    }

    var isStopUpdate = true
    lateinit var updateSeekbarThread: Thread
    fun startSeekbarUpdate() {
        isStopUpdate = false
        updateSeekbarThread = Thread(Runnable {
            while (!isStopUpdate) {
                seekbar.setProgress((videoPlayer.currentPosition * 1.0 / videoPlayer.duration * seekbar.max).toInt())
                Thread.sleep(500)
            }
        })
        updateSeekbarThread.start()
    }

    fun startPlay() {
//        videoPlayer.setDataSource(uri)
        var fileInputStream = FileInputStream(dir)
        videoPlayer.setDataSource(fileInputStream.fd)
        videoPlayer.setOnPreparedListener(
                MediaPlayer.OnPreparedListener {
                    resumePlay()
                    videoPlayer.isLooping = true
                }
        )
        videoPlayer.prepare()
    }

    fun resumePlay() {
        if (!isResume){
            return
        }
        if (curPosition > 0) {
            videoPlayer.seekTo(curPosition)
        }
        startSeekbarUpdate()
        videoPlayer.start()
    }

    fun stopPlay() {
        isStopUpdate = true
        videoPlayer.stop()
        videoPlayer.release()
    }

    fun pausePlay() {
        videoPlayer.pause()
        isStopUpdate = true
    }
}
