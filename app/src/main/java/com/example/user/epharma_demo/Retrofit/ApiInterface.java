package com.example.user.epharma_demo.Retrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by user on 12/14/2017.
 */

public interface ApiInterface {


    @Multipart
    @POST("upload_image.php")
    Call<Model> uploadFile(@Part MultipartBody.Part file);

    @GET("user_login.php")
    Call<Model> userLoginSystem(@Query("email") String email,
                                @Query("password") String password);
}
