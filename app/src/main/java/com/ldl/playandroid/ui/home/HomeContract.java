package com.ldl.playandroid.ui.home;

import com.example.baselibrary.base.BaseContract;
import com.ldl.playandroid.bean.entity.Article;

public class HomeContract {

    interface View extends BaseContract.BaseView{
        void getHomeArticles(Article article,boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void loadHomeArticles();

        void refresh();

        void loadMore();

    }

}
