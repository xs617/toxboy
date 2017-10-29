package until;

/**
 * Created by Administrator on 2017/4/10.
 */

public class TimeUtil {
    /**
     *
     * @param time
     * @return
     */
    static public long getTimeHour(long time){
       return time / 1000 /60 /60;
    }

    static public long getTimeMinute(long time){
        return time / 1000 / 60 % 60;
    }
}
