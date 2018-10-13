package com.ldl.playandroid.ui.knowledgesystem;

import android.view.View;

import com.ldl.playandroid.R;
import com.ldl.playandroid.base.BaseContract;
import com.ldl.playandroid.base.BaseFragment;

public class KnowledgeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }
}
