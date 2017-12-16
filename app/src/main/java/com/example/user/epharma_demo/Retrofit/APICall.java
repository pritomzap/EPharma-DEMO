package com.example.user.epharma_demo.Retrofit;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
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

    public Context c;
    public String userid;

    public APICall(Context c) {
        this.c = c;
    }

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

    public void userRegister(String username, String email, String password) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        call = apiInterface.userRegistration(username, email, password);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                String result = response.body().getMessage();
                Toasty.success(c, result, Toast.LENGTH_LONG, true).show();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }


}
