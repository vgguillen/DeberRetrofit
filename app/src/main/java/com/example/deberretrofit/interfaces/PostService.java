package com.example.deberretrofit.interfaces;

import com.example.deberretrofit.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostService {
    @GET("issues.php")
    Call<List<Post>> find(@Query("j_id")String id);


}
