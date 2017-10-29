package com.wjr.toybox.service.local.mediaplayservice;

/**
 * Created by Administrator on 2017/2/10.
 */

public interface MusicPlayer {
    void play();
    void pause();
    void stop();
    void setVolume(int volume);
    void setProgress(float progress);
}
