package com.wjr.toybox.service.local.mediaplayservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MusicPlayerService extends Service implements MusicPlayer {
    MediaPlayer mediaPlayer = null;
    @Override
    public void play() {
        if (mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource("");
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
    }

    @Override
    public void setVolume(int volume) {

    }

    @Override
    public void setProgress(float progress) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicPlayerServiceBinder();
    }

    public class MusicPlayerServiceBinder extends Binder{
        public MusicPlayerService getMusicPlayerService(){
            return  MusicPlayerService.this;
        }
    }
}
