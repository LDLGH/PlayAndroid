package com.example.baselibrary.router;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 大灯泡 on 2017/4/5.
 * <p>
 * 跳转路由管理
 */

public interface RouterList {

    @Retention(RetentionPolicy.SOURCE)
    @interface HomeDetailActivity {
        String path = "/home/detail";
        String key_url = "url";
        int requestCode = 0x20;
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface LoginActivity {
        String path = "/chat/login";
        int requestCode = 0x20;
    }
}
