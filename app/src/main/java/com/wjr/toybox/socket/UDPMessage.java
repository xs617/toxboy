package com.wjr.toybox.socket;

import android.content.Intent;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * Created by wen on 2017/10/19.
 */

public class UDPMessage implements IMessage{
    String message;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void sendMessage(String message, MessageListener messageListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket(5566);

                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
