package com.ldl.playandroid.ui.my;

import android.view.View;

import com.example.baselibrary.base.BaseContract;
import com.example.baselibrary.base.BaseFragment;
import com.ldl.playandroid.R;

/**
 * Author：LDL Time：2019/3/4
 * Class Comment：
 */
public class MyFragment extends BaseFragment {
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
