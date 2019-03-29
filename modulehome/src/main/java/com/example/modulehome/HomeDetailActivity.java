package com.example.modulehome;

import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.router.RouterList;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;

/**
 * Author：LDL Time：2019/3/20
 * Class Comment：
 */
@Route(path = RouterList.HomeDetailActivity.path)
public class HomeDetailActivity extends BaseActivity<HomeDetailContract.Presenter> implements HomeDetailContract.View {

    @Autowired
    String url;

    @BindView(R2.id.home_container)
    LinearLayout mHomeContainer;

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void initView() {
        mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(mHomeContainer, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    protected HomeDetailContract.Presenter initPresenter() {
        return new HomeDetailPresenter();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
