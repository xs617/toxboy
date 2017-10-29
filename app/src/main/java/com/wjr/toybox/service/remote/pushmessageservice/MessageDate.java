package com.wjr.toybox.service.remote.pushmessageservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MessageDate implements Parcelable{
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int second;
    protected MessageDate(Parcel in) {
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        hour = in.readInt();
        minute = in.readInt();
        second = in.readInt();
    }

    public MessageDate() {
    }

    public static final Creator<MessageDate> CREATOR = new Creator<MessageDate>() {
        @Override
        public MessageDate createFromParcel(Parcel in) {
            return new MessageDate(in);
        }

        @Override
        public MessageDate[] newArray(int size) {
            return new MessageDate[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeInt(second);
    }
}
