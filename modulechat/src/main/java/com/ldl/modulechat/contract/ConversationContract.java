package com.ldl.modulechat.contract;

import com.example.baselibrary.base.BaseContract;

/**
 * Author：LDL Time：2019/3/25
 * Class Comment：
 */
public class ConversationContract {
    public interface View extends BaseContract.BaseView {
        void onRefresh();
    }
    public interface Presenter extends BaseContract.BasePresenter<View> {
        
    }

}
