package com.ldl.modulechat.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.baselibrary.base.BasePresenter;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.ldl.modulechat.contract.LoginContract;
import com.ldl.modulechat.utils.Constant;

import static com.hyphenate.helpdesk.Error.NETWORK_ERROR;
import static com.hyphenate.helpdesk.Error.USER_ALREADY_EXIST;
import static com.hyphenate.helpdesk.Error.USER_AUTHENTICATION_FAILED;
import static com.hyphenate.helpdesk.Error.USER_ILLEGAL_ARGUMENT;

/**
 * 作者：LDL 创建时间：2019/3/25
 * 类说明：
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void registerChat(String account, String password) {
        mView.showLoading();
        ChatClient.getInstance().register(account, password, new Callback() {
            @Override
            public void onSuccess() {
                mView.hideLoading();
            }

            @Override
            public void onError(int code, String error) {
                LogUtils.d("code  " + code + "error  " + error);
                switch (code) {
                    case NETWORK_ERROR://网络不可用
                        mView.hideLoading();
                        ToastUtils.showShort(Constant.NETWORK_ERROR);
                        break;
                    case USER_ALREADY_EXIST://用户已存在
                        ToastUtils.showShort(Constant.USER_ALREADY_EXIST);
                        loginChat(account, password);
                        break;
                    case USER_AUTHENTICATION_FAILED://无开放注册权限（后台管理界面设置[开放|授权]）
                        mView.hideLoading();
                        ToastUtils.showShort(Constant.USER_AUTHENTICATION_FAILED);
                        break;
                    case USER_ILLEGAL_ARGUMENT://用户名非法
                        mView.hideLoading();
                        ToastUtils.showShort(Constant.USER_ILLEGAL_ARGUMENT);
                        break;
                }
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    @Override
    public void loginChat(String account, String password) {
        ChatClient.getInstance().login(account, password, new Callback() {
            @Override
            public void onSuccess() {
                mView.hideLoading();
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LogUtils.d("登录聊天服务器成功！");
                mView.onLogin();
            }

            @Override
            public void onError(int code, String error) {
                mView.hideLoading();
            }

            @Override
            public void onProgress(int progress, String status) {
            }
        });
    }
}
