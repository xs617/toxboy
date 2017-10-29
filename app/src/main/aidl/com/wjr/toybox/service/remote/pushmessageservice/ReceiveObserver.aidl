// ReceiveObserver.aidl
package com.wjr.toybox.service.remote.pushmessageservice;

// Declare any non-default types here with import statements
import com.wjr.toybox.service.remote.pushmessageservice.Message;
interface ReceiveObserver{
    void notifyReceive(in Message message);
}
