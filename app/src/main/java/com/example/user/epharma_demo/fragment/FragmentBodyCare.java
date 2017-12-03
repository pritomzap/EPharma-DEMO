package com.example.user.epharma_demo.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.user.epharma_demo.R;


public class FragmentBodyCare extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String submenu;
    private TextView tv;
    private WebView webView;
    //private OnFragmentInteractionListener mListener;

    public FragmentBodyCare() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentBodyCare(String submenu) {
        this.submenu = submenu;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBodyCare.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBodyCare newInstance(String param1, String param2) {
        FragmentBodyCare fragment = new FragmentBodyCare();
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
        View v = inflater.inflate(R.layout.fragment_body_care, container, false);
        tv = (TextView) v.findViewById(R.id.textview1);
        tv.setText("You Have Selected " + submenu);
        tv.setTextColor(Color.BLUE);
        String url = "https://ratul794.cdipuiu.com/projects/project_1/project/products";
        webView = (WebView) v.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Ignore SSL certificate errors
                //L.d("SSL Error received");

            }
        });
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl(url);
        // Inflate the layout for this fragment
        return v;
    }

}
