package com.example.baselibrary.utils;

import com.blankj.rxbus.RxBus;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
public class RxBusManager {

    public static void post(Event event) {
        RxBus.getDefault().post(event);
    }

    public static void register(Object subscriber, onEventListener listener) {
        RxBus.getDefault().subscribe(subscriber, new RxBus.Callback<Event>() {
            @Override
            public void onEvent(Event event) {
                listener.onEvent(event);
            }
        });
    }

    public static void unregister(Object subscriber) {
        RxBus.getDefault().unregister(subscriber);
    }

    public interface onEventListener {
        void onEvent(Event event);
    }
}
