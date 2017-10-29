package com.wjr.toybox.service.remote.counterservice

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by wen on 2017/9/22.
 */
data class Count(var count: Int, var duration: Int) :Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeInt(duration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Count> {
        override fun createFromParcel(parcel: Parcel): Count {
            return Count(parcel)
        }

        override fun newArray(size: Int): Array<Count?> {
            return arrayOfNulls(size)
        }
    }

}