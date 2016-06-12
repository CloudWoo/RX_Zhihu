package com.haomiao.cloud.rx_zhihu.event;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author thanatos
 * @create 2016-01-05
 **/
public class Events<T> {

    public static final int BANNER_DRAG_STATE = 11;
    public static final int BANNER_VISIBILITY = 21;
    public static final int BANNER_ISALIVE = 31;


    @IntDef({BANNER_DRAG_STATE, BANNER_VISIBILITY, BANNER_ISALIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventCode {}


    public @Events.EventCode int what;
    public T message;

    public static <O> Events<O> just(O t) {
        Events<O> events = new Events<>();
        events.message = t;
        return events;
    }

    public <T> T getMessage() {
        return (T) message;
    }

}
