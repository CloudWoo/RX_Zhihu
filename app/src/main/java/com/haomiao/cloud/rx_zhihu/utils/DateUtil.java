package com.haomiao.cloud.rx_zhihu.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.Utils.
 * Created by Cloud on 16/5/30.
 * Instruction
 */
public class DateUtil {
    private static DateUtil INSTANCE;

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DateUtil();
        }
        return INSTANCE;
    }


    public String getDateTitle(int pageNum) {
        String dayOfWeek = "";
        DateTime dateTime = new DateTime();
        switch (dateTime.minusDays(pageNum).getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                dayOfWeek = "星期日";
                break;
            case DateTimeConstants.MONDAY:
                dayOfWeek = "星期一";
                break;
            case DateTimeConstants.TUESDAY:
                dayOfWeek = "星期二";
                break;
            case DateTimeConstants.WEDNESDAY:
                dayOfWeek = "星期三";
                break;
            case DateTimeConstants.THURSDAY:
                dayOfWeek = "星期四";
                break;
            case DateTimeConstants.FRIDAY:
                dayOfWeek = "星期五";
                break;
            case DateTimeConstants.SATURDAY:
                dayOfWeek = "星期六";
                break;
        }
        return dateTime.minusDays(pageNum).toString("MM月dd日 " + dayOfWeek);

    }

    public String getDate(int pageNum) {
        DateTime dateTime = new DateTime();
        return dateTime.plusDays(1).minusDays(pageNum).toString("yyyyMMdd");
    }

}
