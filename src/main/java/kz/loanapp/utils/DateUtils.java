package kz.loanapp.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ardak on 9/22/17.
 */
public class DateUtils {

    public static Date getTimeFrameDate(Date dateNow, long timeframe){
        long secs = (dateNow.getTime())/1000;
        secs = secs - timeframe;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(secs*1000L);
        Date timeFrameDate = calendar.getTime();
        return timeFrameDate;
    }

}
