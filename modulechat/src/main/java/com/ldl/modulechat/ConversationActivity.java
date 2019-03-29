package com.ldl.modulechat;

import android.support.v7.widget.RecyclerView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.utils.Event;
import com.example.baselibrary.utils.EventCode;
import com.ldl.modulechat.adapter.ConversationAdapter;
import com.ldl.modulechat.contract.ConversationContract;
import com.ldl.modulechat.presenter.ConversationPresenter;

import butterknife.BindView;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
public class ConversationActivity extends BaseActivity<ConversationPresenter> implements ConversationContract.View {


    @BindView(R2.id.rv_conversation)
    RecyclerView mRvConversation;

    private ConversationAdapter mAdapter;

    @Override
    protected boolean isRegisterRxBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void initView() {
        mAdapter = new ConversationAdapter();
        mRvConversation.setAdapter(mAdapter);
    }

    @Override
    protected ConversationPresenter initPresenter() {
        return new ConversationPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mAdapter.setNewData(mPresenter.loadConversationList());
    }

    @Override
    protected void receiveEvent(Event event) {
        if (event.getCode().equals(EventCode.CHAT_HELPER)) {
            onRefresh();
        }
    }
}
