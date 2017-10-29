// IClockAIDL.aidl
package com.wjr.toybox.service.remote.counterservice;

// Declare any non-default types here with import statements
import com.wjr.toybox.service.remote.counterservice.Count;
interface ICounter {
    Count getCurrentCount();
    void setDuration(int duration);
    void stopCount();
    void reset();
}
