package com.wjr.toybox.service.remote.pushmessageservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/2/10.
 */

public class Message implements Parcelable {
    private String message;
    private String from;
    private MessageDate date;
    protected Message(Parcel in) {
        message =  in.readString();
        from = in.readString();
        date = in.readParcelable(MessageDate.class.getClassLoader());
    }

    public Message() {
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(from);
        dest.writeParcelable(date,0);
    }

    public String getMessage() {
        return message;
    }


    public String getFrom() {
        return from;
    }


    public MessageDate getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setDate(MessageDate date) {
        this.date = date;
    }
}
