package com.tenuchon.developerslife.utils;

import com.tenuchon.developerslife.model.Dto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GifApi {
    String URL = "https://developerslife.ru";

    @GET("/latest/0")
    Call<Dto> getLatestGif(@Query("json") String json,
                           @Query("pageSize") String pageSize
    );

    @GET("/hot/0")
    Call<Dto> getHotGif(@Query("json") String json,
                        @Query("pageSize") String pageSize
    );

    @GET("/top/0")
    Call<Dto> getTopGif(@Query("json") String json,
                        @Query("pageSize") String pageSize
    );
}
