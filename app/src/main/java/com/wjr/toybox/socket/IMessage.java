package com.wjr.toybox.socket;

/**
 * Created by wen on 2017/10/19.
 */

public interface IMessage{
    String getMessage();
    void sendMessage(String message,MessageListener messageListener);
}
