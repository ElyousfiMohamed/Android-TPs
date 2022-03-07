package com.example.myapplication.service;

import com.example.myapplication.model.Article;
import com.example.myapplication.model.ListArticleResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestServiceAPI {
    @GET("everything/")
    Call<ListArticleResponse> articles(@Query("q") String key, @Query("from") String date, @Query("apikey") String apikey);
}
