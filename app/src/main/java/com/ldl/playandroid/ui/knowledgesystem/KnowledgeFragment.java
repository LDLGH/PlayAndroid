package com.ldl.playandroid.ui.knowledgesystem;

import android.view.View;

import com.example.baselibrary.base.BaseContract;
import com.example.baselibrary.base.BaseFragment;
import com.ldl.playandroid.R;

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
