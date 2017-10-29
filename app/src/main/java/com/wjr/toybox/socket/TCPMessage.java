package com.wjr.toybox.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wen on 2017/10/19.
 */

public class TCPMessage implements IMessage {
    String message = "";
    Socket socket;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void sendMessage(final String message, final MessageListener messageListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (socket == null || socket.isClosed() || !socket.isConnected()) {
                        socket = new Socket("192.168.0.104", 5556);
                    }
                    OutputStream outputStream = socket.getOutputStream();

                    byte[] data = message.getBytes("UTF-8");
                    byte[] length = int2Byte(data.length);
                    outputStream.write(length);
                    outputStream.write(data);
                    outputStream.flush();

                    BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                    byte[] messageLength = new byte[4];
                    bufferedInputStream.read(messageLength, 0, messageLength.length);
                    int inputLength = byte2Int(messageLength);
                    if (inputLength > 0) {
                        byte[] inputData = new byte[inputLength];
                        bufferedInputStream.read(inputData, 0, inputLength);


                        if (messageListener != null) {
                            messageListener.onReceive(new String(inputData));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private byte[] int2Byte(int input) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (input & 0xff);
        bytes[1] = (byte) (input >> 8 & 0xff);
        bytes[2] = (byte) (input >> 16 & 0xff);
        bytes[3] = (byte) (input >> 24 & 0xff);
        return bytes;
    }

    private int byte2Int(byte[] lengthByte) {
        if (lengthByte.length != 4) {
            return 0;
        }
        int value = 0;
        for (int i = 0; i < lengthByte.length; i++) {
            value += (int) lengthByte[i] << i * 8;
        }
        return value;
    }

}
