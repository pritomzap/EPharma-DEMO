package com.example.user.epharma_demo.Inner_Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.epharma_demo.MainActivity;
import com.example.user.epharma_demo.R;
import com.example.user.epharma_demo.Retrofit.APICall;
import com.example.user.epharma_demo.Retrofit.ApiClient;
import com.example.user.epharma_demo.Retrofit.ApiInterface;
import com.example.user.epharma_demo.Retrofit.Model;
import com.wonderkiln.camerakit.CameraKit;
import com.wonderkiln.camerakit.CameraKitEventCallback;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Camera extends AppCompatActivity {
    public Bitmap bitmap;
    public APICall apiCall;
    public String tag = "CAMERA";
    //public EditText imgtitle;
    public ApiInterface apiInterface;
    CameraView cameraView;
    private int cameraMethod = CameraKit.Constants.METHOD_STANDARD;
    private boolean cropOutput = false;
    private ImageButton im, bk;

    //private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        getSupportActionBar().hide();
        cameraView = (CameraView) findViewById(R.id.camera);
        im = (ImageButton) findViewById(R.id.btn);
        //imgtitle = (EditText)findViewById(R.id.imgtitle);
        bk = (ImageButton) findViewById(R.id.backbtn);
        cameraView.setMethod(cameraMethod);
        cameraView.setCropOutput(cropOutput);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraView.captureImage(new CameraKitEventCallback<CameraKitImage>() {
                    @Override
                    public void callback(CameraKitImage cameraKitImage) {
                        byte[] jpeg = cameraKitImage.getJpeg();

                        //long callbackTime = System.currentTimeMillis();
                        bitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
                        imageAlartBox(bitmap);
                    }
                });
            }
        });

        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }


    public File tempFileWrite(Bitmap bitmap) {


        File file = new File(getBaseContext().getCacheDir(), "TEMP");
        try {
            file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }


    public void apiCall(File writtenFile) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), writtenFile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", writtenFile.getName(), requestBody);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Model> call = apiInterface.uploadFile(fileToUpload);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model model = response.body();
                if (model != null) {
                    if (model.isSuccess())
                        Toasty.success(getBaseContext(), model.getMessage(), Toast.LENGTH_SHORT, true).show();
                    else
                        Toasty.error(getBaseContext(), model.getMessage(), Toast.LENGTH_SHORT, true).show();
                } else
                    Toasty.error(getBaseContext(), "Server response is null", Toast.LENGTH_SHORT, true).show();

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    public void imageAlartBox(final Bitmap bitmap) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Camera.this);
        LayoutInflater inflater = LayoutInflater.from(Camera.this);
        final View dialogView = inflater.inflate(R.layout.camera_image_view, null);
        builder.setCancelable(false);
        builder.setView(dialogView);
        Button cancel = (Button) dialogView.findViewById(R.id.cancel);
        Button upload = (Button) dialogView.findViewById(R.id.upload);
        ImageView imageView = (ImageView) dialogView.findViewById(R.id.cameraImage);
        imageView.setImageBitmap(bitmap);
        final AlertDialog dialog = builder.create();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = tempFileWrite(bitmap);
                apiCall(file);
                dialog.cancel();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(1000, 900);
    }


}
