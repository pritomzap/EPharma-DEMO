package com.example.user.epharma_demo.Retrofit;

import android.content.Context;

import retrofit2.Call;

/**
 * Created by user on 12/14/2017.
 */

public class APICall {

    public ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    public Call<Model> call;
    public String message;
    Context c;

    public APICall(Context c) {
        this.c = c;
    }


}
