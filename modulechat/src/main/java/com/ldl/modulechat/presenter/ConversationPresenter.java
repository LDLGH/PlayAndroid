package com.ldl.modulechat.presenter;

import android.util.Pair;

import com.example.baselibrary.base.BasePresenter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.ldl.modulechat.contract.ConversationContract;
import com.ldl.modulechat.utils.NormalConversation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
public class ConversationPresenter extends BasePresenter<ConversationContract.View> implements ConversationContract.Presenter {


    /**
     * 获取所有会话
     */
    public List<NormalConversation> loadConversationList() {
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<>();
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            // 内部是TimSort算法，有bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<NormalConversation> list = new ArrayList<>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(new NormalConversation(sortItem.second));
        }
        return list;
    }

    /**
     * 根据最后一条消息的时间戳排序对话
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, (con1, con2) -> {
            if (con1.first.equals(con2.first)) {
                return 0;
            } else if (con2.first > con1.first) {
                return 1;
            } else {
                return -1;
            }
        });
    }

}
