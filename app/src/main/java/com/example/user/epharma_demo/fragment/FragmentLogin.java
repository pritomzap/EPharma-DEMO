package com.example.user.epharma_demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.epharma_demo.MainActivity;
import com.example.user.epharma_demo.Others.SharedPref;
import com.example.user.epharma_demo.R;
import com.example.user.epharma_demo.Retrofit.APICall;


public class FragmentLogin extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public APICall apiCall;
    public SharedPref sharedPref;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //private OnFragmentInteractionListener mListener;
    private EditText email, password;
    private Button login;

    public FragmentLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
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
        final View v = inflater.inflate(R.layout.fragment_login, container, false);
        email = (EditText) v.findViewById(R.id.edittext1);
        password = (EditText) v.findViewById(R.id.edittext2);
        login = (Button) v.findViewById(R.id.button1);
        sharedPref = new SharedPref(v.getContext());
        apiCall = new APICall(v);
        final Context c = v.getContext();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eMail = email.getText().toString();
                String pass = password.getText().toString();
                apiCall.userLogin(eMail, pass);
                sharedPref.saveInfo(eMail, pass, "1");
                Intent intent = new Intent(c, MainActivity.class);
                startActivity(intent);

            }
        });
        return v;
    }

}
