package com.ldl.modulechat.utils;

import android.content.Context;

import com.example.baselibrary.base.BaseModuleApplication;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

/**
 * Created by LDL on 2017/6/14.
 */

public class NormalConversation extends Conversation {

    private EMConversation conversation;

    //最后一条消息
    private EMMessage lastMessage;
//    private ContactsInfo contactsInfo;

    public NormalConversation(EMConversation conversation) {
        this.conversation = conversation;
        type = conversation.getType();
        lastMessage = conversation.getLastMessage();
        conversationId = lastMessage.conversationId();
//        contactsInfo = ContactsInfoBackupsDAO.i().queryUser(conversationId);
    }

    /**
     * 获取头像
     */
    @Override
    public String getAvatar() {
//        if(contactsInfo != null){
//            return contactsInfo.getFriendPic();
//        }
        return "";
    }

    /**
     * 跳转到聊天界面或会话详情
     *
     * @param context 跳转上下文
     */
    @Override
    public void navToDetail(Context context) {

    }

    /**
     * 获取最后一条消息摘要
     */
    @Override
    public String getLastMessageSummary() {
        if (lastMessage == null) {
            return "";
        }
        return EaseCommonUtils.getMessageDigest(lastMessage, BaseModuleApplication.getAppContext());
    }

    /**
     * 获取名称
     */
    @Override
    public String getName() {
//        if(contactsInfo != null){
//            name = contactsInfo.getFriendName();
//        }
        return name;
    }

    /**
     * 获取未读消息数量
     */
    @Override
    public long getUnreadNum() {
        if (conversation == null) return 0;
        return conversation.getUnreadMsgCount();
    }

    /**
     * 将所有消息标记为已读
     */
    @Override
    public void readAllMessage() {
        if (conversation != null) {
            conversation.markAllMessagesAsRead();
        }
    }

    /**
     * 删除和某个user会话，如果需要保留聊天记录，传false
     */
    @Override
    public void deleteConversation(boolean deleteMessages) {
        if (conversation != null) {
            ChatClient.getInstance().chatManager().deleteConversation(conversationId, deleteMessages);
        }
    }

    /**
     * 获取最后一条消息的时间
     */
    @Override
    public long getLastMessageTime() {
        if (lastMessage == null) return 0;
        return lastMessage.getMsgTime();
    }

    /**
     * 获取会话类型
     */
    public EMConversation.EMConversationType getType() {
        return conversation.getType();
    }
}
