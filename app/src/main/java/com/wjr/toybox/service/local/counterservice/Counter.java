package com.wjr.toybox.service.local.counterservice;

/**
 * Created by Administrator on 2017/2/9.
 */

public interface Counter {
    int getCount();
    void start();
    void stop();
    void reset();
}
