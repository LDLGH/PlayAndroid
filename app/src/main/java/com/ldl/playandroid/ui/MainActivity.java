package com.ldl.playandroid.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.base.BaseContract;
import com.ldl.playandroid.R;
import com.ldl.playandroid.ui.home.HomeFragment;
import com.ldl.playandroid.ui.knowledgesystem.KnowledgeFragment;
import com.ldl.playandroid.ui.my.MyFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    private HomeFragment homeFragment;
    private KnowledgeFragment knowledgeFragment;
    private MyFragment myFragment;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_main;
    }

    @Override
    protected void initView() {
        fm = getSupportFragmentManager();
        mNavigation.setOnNavigationItemSelectedListener(this);
        mNavigation.setSelectedItemId(R.id.navigation_home);
        //新的分支
    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @SuppressLint("CommitTransaction")
    private void hideFragment() {
        ft = fm.beginTransaction();
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (knowledgeFragment != null) {
            ft.hide(knowledgeFragment);
        }
        if (myFragment != null) {
            ft.hide(myFragment);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        hideFragment();
        switch (item.getItemId()) {
            case R.id.navigation_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.layout_fragment, homeFragment, "homeFragment");
                } else {
                    ft.show(homeFragment);
                }
                ft.commitAllowingStateLoss();
                break;
            case R.id.navigation_knowledgesystem:
                if (knowledgeFragment == null) {
                    knowledgeFragment = new KnowledgeFragment();
                    ft.add(R.id.layout_fragment, knowledgeFragment, "knowledgeFragment");
                } else {
                    ft.show(knowledgeFragment);
                }
                ft.commitAllowingStateLoss();
                break;
            case R.id.navigation_my:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    ft.add(R.id.layout_fragment, myFragment, "myFragment");
                } else {
                    ft.show(myFragment);
                }
                ft.commitAllowingStateLoss();
                break;
        }
        return true;
    }
}
