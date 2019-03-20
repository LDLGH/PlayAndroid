package com.ldl.playandroid.ui.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselibrary.base.BaseFragment;
import com.example.baselibrary.router.RouterList;
import com.ldl.playandroid.R;
import com.ldl.playandroid.bean.entity.Article;

import java.util.Objects;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_article)
    RecyclerView rv_article;

    private HomeAdapter homeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        homeAdapter = new HomeAdapter(R.layout.item_article);
        rv_article.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_article.setAdapter(homeAdapter);
        homeAdapter.setEnableLoadMore(false);
        homeAdapter.setOnItemClickListener((adapter, view1, position) -> ARouter.getInstance().build(RouterList.HomeDetailActivity.path)
                .withString(RouterList.HomeDetailActivity.key_url, Objects.requireNonNull(homeAdapter.getItem(position)).getLink())
                .navigation());
        homeAdapter.setOnLoadMoreListener(() -> mPresenter.loadMore(), rv_article);
        SwipeRefreshLayout.OnRefreshListener listener = () -> mPresenter.refresh();
        mSwipeRefreshLayout.setOnRefreshListener(listener);

        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
        listener.onRefresh();
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void getHomeArticles(Article article, boolean isRefresh) {
        if (isRefresh) {
            homeAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);
            homeAdapter.setNewData(article.getDatas());
        } else {
            homeAdapter.loadMoreComplete();
            homeAdapter.addData(article.getDatas());
        }
    }
}
