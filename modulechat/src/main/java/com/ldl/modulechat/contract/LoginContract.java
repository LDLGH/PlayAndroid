package com.ldl.modulechat.contract;

import com.example.baselibrary.base.BaseContract;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
public class LoginContract {

    public interface View extends BaseContract.BaseView {
        void onLogin();
    }

    public interface Presenter extends BaseContract.BasePresenter<View> {
        /**
         * @描述 环信聊天注册
         * @参数 account 帐号，password 密码
         * @创建人 LDL
         * @创建时间 2019/3/25
         */
        void registerChat(String account, String password);

        /**
         * @描述 环信聊天登录
         * @参数 account 帐号，password 密码
         * @创建人 LDL
         * @创建时间 2019/3/25
         */
        void loginChat(String account, String password);
    }
}
