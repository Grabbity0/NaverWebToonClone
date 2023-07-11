package com.example.naverwebtoonrenewal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("Grabbity0/myTestJSon/main/NaverWebToon/webtoon.json")
    Call<ComicsPojo> getWebToons();
}
