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

    @GET("image_upload.php")
    Call<Model> uploadImage(@Query("image") String image,
                            @Query("title") String title);

    @Multipart
    @POST("upload_image.php")
    Call<Model> uploadFile(@Part MultipartBody.Part file);
}
