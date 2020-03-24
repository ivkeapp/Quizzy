package com.example.quizzy.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetSelectedQuestions {

    @GET("fetch_all.php")
    Call<String> getAllNews();

}
