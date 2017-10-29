package com.wjr.toybox.effect.slideDeletion;

/**
 * Created by Administrator on 2017/2/22.
 */

public class DataWrapped {
    private Data data;
    boolean isSlide;

    public DataWrapped(String message) {
        data = new Data(message);
        isSlide = false;
    }

    public void setMessage(String message){
        data.message = message;
    }

    public String getMessage(){
        return data.message;
    }
}
