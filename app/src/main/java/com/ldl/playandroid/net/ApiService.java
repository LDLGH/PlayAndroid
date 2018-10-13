package com.ldl.playandroid.net;

import com.ldl.playandroid.bean.entity.Article;
import com.ldl.playandroid.bean.net.DataResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {


    /**
     *  首页数据
     *
     *  @param page
     *
     * */
    @GET("/article/list/{page}/json")
    Observable<DataResponse<Article>> getHomeArticles(@Path("page") int page);

}
