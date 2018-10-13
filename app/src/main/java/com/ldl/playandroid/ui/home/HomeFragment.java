package com.ldl.playandroid.ui.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ldl.playandroid.R;
import com.ldl.playandroid.base.BaseFragment;
import com.ldl.playandroid.bean.entity.Article;
import com.ldl.playandroid.ui.WebActivity;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View{

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
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url",homeAdapter.getItem(position).getLink());
                startActivity(intent);
            }
        });
        homeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        },rv_article);
        SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                mPresenter.refresh();
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(listener);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh();
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void getHomeArticles(Article article,boolean isRefresh) {
        if(isRefresh){
            homeAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);
            homeAdapter.setNewData(article.getDatas());
        }else {
            homeAdapter.loadMoreComplete();
            homeAdapter.addData(article.getDatas());
        }
    }
}
