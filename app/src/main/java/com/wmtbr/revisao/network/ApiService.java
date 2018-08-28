package com.wmtbr.revisao.network;

import com.wmtbr.revisao.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/posts")
    Call<List<Posts>> getAllPosts();


}
