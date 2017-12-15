package com.example.user.epharma_demo.Retrofit;

import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 12/14/2017.
 */

public class APICall {

    public ApiInterface apiInterface;
    public Call<Model> call;
    public String message;
    public View v;

    public String userid;

    public APICall(View view) {
        this.v = v;
    }

    public String userLogin(final String email, final String password) {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        call = apiInterface.userLoginSystem(email, password);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                userid = response.body().getUserID().toString();
                //SharedPref sharedPref = new SharedPref(v);
                //Toasty.success(c,userid, Toast.LENGTH_LONG,false).show();

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
        return userid;

    }


}
