package com.ldl.modulechat.utils;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.baselibrary.utils.Event;
import com.example.baselibrary.utils.EventCode;
import com.example.baselibrary.utils.RxBusManager;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.ChatManager;
import com.hyphenate.chat.Message;

import java.util.List;

import static com.hyphenate.EMError.USER_AUTHENTICATION_FAILED;
import static com.hyphenate.EMError.USER_LOGIN_ANOTHER_DEVICE;
import static com.hyphenate.EMError.USER_REMOVED;
import static com.hyphenate.chat.adapter.EMAError.USER_NOT_FOUND;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
public class ChatHelper implements IChatHelper {

    private static final String APPKEY = "1422190307068128#kefuchannelapp67233";
    private static final String TENANTID = "67233";
    private static final String TAG = "ChatHelper";

    public static ChatHelper instance = new ChatHelper();

    public void init(Context context) {
        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey(APPKEY);
        options.setTenantId(TENANTID);
        options.setConsoleLog(true);
        // 环信客服 SDK 初始化, 初始化成功后再调用环信下面的内容
        if (ChatClient.getInstance().init(context, options)) {
            LogUtils.d("初始化成功");
            addConnectionListener();
            addMessageListener();
        }
    }

    @Override
    public void addConnectionListener() {
        ChatClient.getInstance().addConnectionListener(new ChatClient.ConnectionListener() {
            @Override
            public void onConnected() {
                //成功连接到服务器
                LogUtils.d("成功连接到服务器");
            }

            @Override
            public void onDisconnected(int errorcode) {
                //errorcode的值
                //Error.USER_REMOVED 账号移除
                //Error.USER_LOGIN_ANOTHER_DEVICE 账号在其他地方登录
                //Error.USER_AUTHENTICATION_FAILED 账号密码错误
                //Error.USER_NOT_FOUND  账号找不到
                switch (errorcode) {
                    case USER_REMOVED:
                        ToastUtils.showShort("账号移除");
                        break;
                    case USER_LOGIN_ANOTHER_DEVICE:
                        ToastUtils.showShort("账号在其他地方登录");
                        break;
                    case USER_AUTHENTICATION_FAILED:
                        ToastUtils.showShort("账号密码错误");
                        break;
                    case USER_NOT_FOUND:
                        ToastUtils.showShort("账号找不到");
                        break;
                }
            }
        });
    }

    @Override
    public void addMessageListener() {
        ChatClient.getInstance().chatManager().addMessageListener(new ChatManager.MessageListener() {
            @Override
            public void onMessage(List<Message> list) {
                //收到普通消息
                Event<String> event = new Event<>(EventCode.CHAT_HELPER);
                RxBusManager.post(event);
            }

            @Override
            public void onCmdMessage(List<Message> list) {
                //收到命令消息，命令消息不存数据库，一般用来作为系统通知，例如留言评论更新，
                //会话被客服接入，被转接，被关闭提醒
            }

            @Override
            public void onMessageStatusUpdate() {
                //消息的状态修改，一般可以用来刷新列表，显示最新的状态
            }

            @Override
            public void onMessageSent() {
                //发送消息后，会调用，可以在此刷新列表，显示最新的消息
            }
        });
    }
}
