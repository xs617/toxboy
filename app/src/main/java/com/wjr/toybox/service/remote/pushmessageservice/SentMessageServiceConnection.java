package com.wjr.toybox.service.remote.pushmessageservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/10.
 */

public class SentMessageServiceConnection implements ServiceConnection {
    private ArrayList<ReceiveObserver> receiveObservers = new ArrayList<ReceiveObserver>();
    private MessageManager messageManager = null;
    public MessageManager getMessageManager(){
        return messageManager;
    }
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        if (service != null) {
            try {
                messageManager = MessageManager.Stub.asInterface(service);
                Log.e("service",messageManager.toString());
                for (ReceiveObserver receiveObserver :receiveObservers) {
                    messageManager.addReceiveObserver(receiveObserver);
                }
                receiveObservers.clear();
            }catch (Exception e){
                e.printStackTrace();
                messageManager = null;
            }
        }else{
            Log.e("service","service is null");
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        messageManager = null;
    }

    public void addReceivedObserver(ReceiveObserver receiveObserver){
        if (messageManager != null) {
            try {
                messageManager.addReceiveObserver(receiveObserver);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            receiveObservers.add(receiveObserver);
        }
    }
}
