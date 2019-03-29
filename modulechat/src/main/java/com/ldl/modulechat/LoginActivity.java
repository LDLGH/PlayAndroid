package com.ldl.modulechat;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.router.RouterList;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.ldl.modulechat.contract.LoginContract;
import com.ldl.modulechat.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
@Route(path = RouterList.LoginActivity.path)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R2.id.et_username)
    EditText mEtUsername;
    @BindView(R2.id.et_password)
    EditText mEtPassword;
    @BindView(R2.id.btn_login)
    Button mBtnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            finish();
        }
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R2.id.btn_login})
    public void loginAction(){
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        if (StringUtils.isTrimEmpty(username)) {
            ToastUtils.showShort("请输入用户名");
            return;
        }
        if (StringUtils.isTrimEmpty(password)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (!ChatClient.getInstance().isLoggedInBefore()) {
            //未登录，需要登录后，再进入会话界面
            mPresenter.registerChat(username,password);
        }
    }

    @Override
    public void onLogin() {

    }
}
