package com.example.quizzy.retrofit;

import android.util.Log;

public class APIUtils {

    public static final String BASE_URL = "https://ivanzarkovic.com/quizzy/";

    public static GetSelectedQuestions getAPIServiceFetchAll() {
        Log.d("responser", "BASE_URL "+BASE_URL);
        return RetrofitClient.getClient(BASE_URL).create(GetSelectedQuestions.class);
    }

}
