package com.ldl.playandroid.ui.home;

import com.ldl.playandroid.base.BasePresenter;
import com.ldl.playandroid.bean.entity.Article;
import com.ldl.playandroid.bean.net.DataResponse;
import com.ldl.playandroid.net.ApiService;
import com.ldl.playandroid.net.RetrofitManager;
import com.ldl.playandroid.utils.RxSchedulers;

import io.reactivex.functions.Consumer;

public class HomePresenter extends BasePresenter<HomeContract.View>implements HomeContract.Presenter {


    private int page = 0;

    @Override
    public void loadHomeArticles() {
        RetrofitManager.create(ApiService.class)
                .getHomeArticles(page)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> articleDataResponse) throws Exception {
                        mView.getHomeArticles(articleDataResponse.getData(),page ==0);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFailed(throwable.getMessage());
                    }
                });
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
