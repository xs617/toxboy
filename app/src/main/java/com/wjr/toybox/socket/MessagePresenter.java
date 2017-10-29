package com.wjr.toybox.socket;

/**
 * Created by wen on 2017/10/19.
 */

public class MessagePresenter implements IPresenter{
    IMessage mMessage;
    IMessageView mView;

    public MessagePresenter(IMessageView mView) {
        this.mView = mView;
    }

    MessageListener messageListener = new MessageListener() {
        @Override
        public void onReceive(String message) {
            mView.showMessage(message);
        }
    };

    @Override
    public void sendMessage() {
        if (mMessage == null){
            mMessage = new TCPMessage();
        }
        mMessage.sendMessage(mView.getMessage(),messageListener);
    }


}
