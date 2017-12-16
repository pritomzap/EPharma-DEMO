package com.example.user.epharma_demo.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.epharma_demo.Inner_Activities.Camera;
import com.example.user.epharma_demo.Inner_Activities.Emergency;
import com.example.user.epharma_demo.Inner_Activities.Order_History;
import com.example.user.epharma_demo.Inner_Activities.Request_Product;
import com.example.user.epharma_demo.R;
import com.example.user.epharma_demo.Retrofit.ApiClient;
import com.example.user.epharma_demo.Retrofit.ApiInterface;
import com.example.user.epharma_demo.Retrofit.Model;

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

import static android.app.Activity.RESULT_OK;


public class Fragment_content extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private static final int SELECT_PICTURE = 100;
    private static int RESULT_LOAD_IMAGE = 1;
    public Bitmap bitmap;
    public ApiInterface apiInterface;
    public View v2;
    ImageButton im;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton imageButton2, imageButton5, imageButton6, imageButton3, imageButton4, imageButton7;
    public Fragment_content() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_content.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_content newInstance(String param1, String param2) {
        Fragment_content fragment = new Fragment_content();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_content, container, false);
        imageButton2 = (ImageButton) v.findViewById(R.id.imageButton2);
        imageButton5 = (ImageButton) v.findViewById(R.id.imageButton5);
        imageButton6 = (ImageButton) v.findViewById(R.id.imageButton6);
        imageButton3 = (ImageButton) v.findViewById(R.id.imageButton3);
        imageButton4 = (ImageButton) v.findViewById(R.id.imageButton4);
        imageButton7 = (ImageButton) v.findViewById(R.id.imageButton7);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), Camera.class);
                startActivity(intent);
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), Order_History.class);
                startActivity(intent);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), Emergency.class);
                startActivity(intent);
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "01824297068";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
            }
        });

        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(v.getContext(), Request_Product.class);
                startActivity(intent);
            }
        });

        v2 = v;
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = tempFileWrite(bitmap);
            apiCall(file, v2);
        }
    }


    public void apiCall(File writtenFile, final View v) {
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
                        Toasty.success(v.getContext(), model.getMessage(), Toast.LENGTH_SHORT, true).show();
                    else
                        Toasty.error(v.getContext(), model.getMessage(), Toast.LENGTH_SHORT, true).show();
                } else
                    Toasty.error(v.getContext(), "Server response is null", Toast.LENGTH_SHORT, true).show();

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });

    }


    public File tempFileWrite(Bitmap bitmap) {


        File file = new File(this.getContext().getCacheDir(), "TEMP2");
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


}
