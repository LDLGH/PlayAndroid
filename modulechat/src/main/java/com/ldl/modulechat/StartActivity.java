package com.ldl.modulechat;

import android.os.Handler;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.base.BaseContract;
import com.example.baselibrary.router.RouterList;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;

import java.util.List;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
public class StartActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PermissionUtils.permission(PermissionConstants.PHONE, PermissionConstants.STORAGE).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {

            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {

            }
        }).request();

        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
        }
        new Handler().postDelayed(() -> {
            if (ChatClient.getInstance().isLoggedInBefore()) {

            } else {
                ARouter.getInstance().build(RouterList.LoginActivity.path).navigation();
            }
            finish();
        }, 3000);
    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }
}
