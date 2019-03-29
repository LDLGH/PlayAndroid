package com.ldl.modulechat.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.util.DateUtils;
import com.ldl.modulechat.R;
import com.ldl.modulechat.utils.NormalConversation;

import java.util.Date;

/**
 * Author：LDL Time：2019/3/11
 * Class Comment：
 */
public class ConversationAdapter extends BaseQuickAdapter<NormalConversation, BaseViewHolder> {

    public ConversationAdapter() {
        super(R.layout.item_conversation);
    }

    @Override
    protected void convert(BaseViewHolder helper, NormalConversation item) {
        helper.setText(R.id.tv_nickname, item.getName());
        helper.setText(R.id.tv_message, item.getLastMessageSummary());
        helper.setText(R.id.tv_time, DateUtils.getTimestampString(new Date(item.getLastMessageTime())));
        helper.setVisible(R.id.tv_unread, item.getUnreadNum() > 0);
        helper.setText(R.id.tv_unread, String.valueOf(item.getUnreadNum()));
    }
}
