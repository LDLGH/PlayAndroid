package com.ldl.playandroid.ui.home;

import com.example.baselibrary.base.BasePresenter;
import com.example.baselibrary.net.RetrofitManager;
import com.example.baselibrary.utils.RxSchedulers;
import com.ldl.playandroid.net.ApiService;

import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {


    private int page = 0;

    @Override
    public void loadHomeArticles() {
        Disposable subscribe = RetrofitManager.create(ApiService.class)
                .getHomeArticles(page)
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(articleDataResponse -> mView.getHomeArticles(articleDataResponse.getData(), page == 0), throwable -> mView.showFailed(throwable.getMessage()));
    }

    @Override
    public void refresh() {
        page = 0;
        loadHomeArticles();
    }

    @Override
    public void loadMore() {
        page++;
        loadHomeArticles();
    }

}
