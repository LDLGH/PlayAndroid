package com.ldl.modulechat;

import com.example.baselibrary.base.BaseActivity;
import com.ldl.modulechat.contract.ChatContract;
import com.ldl.modulechat.presenter.ChatPresenter;

/**
 * Author：LDL Time：2019/3/26
 * Class Comment：
 */
public class ChatActivity extends BaseActivity<ChatPresenter> implements ChatContract.View {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected ChatPresenter initPresenter() {
        return new ChatPresenter();
    }
}
