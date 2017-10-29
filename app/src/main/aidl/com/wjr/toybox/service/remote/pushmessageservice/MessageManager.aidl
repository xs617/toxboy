// SendMessageService.aidl
package com.wjr.toybox.service.remote.pushmessageservice;

// Declare any non-default types here with import statements
import com.wjr.toybox.service.remote.pushmessageservice.Message;
import com.wjr.toybox.service.remote.pushmessageservice.ReceiveObserver;
interface MessageManager {
    void sent(in Message message);
    void addReceiveObserver(in ReceiveObserver receiveObserver);
}
