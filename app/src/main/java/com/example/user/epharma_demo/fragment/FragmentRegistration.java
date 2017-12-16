package com.example.user.epharma_demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.epharma_demo.MainActivity;
import com.example.user.epharma_demo.R;
import com.example.user.epharma_demo.Retrofit.APICall;

import es.dmoral.toasty.Toasty;


public class FragmentRegistration extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText name, email, pass;
    private Button submit;
    //private OnFragmentInteractionListener mListener;

    public FragmentRegistration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegistration.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegistration newInstance(String param1, String param2) {
        FragmentRegistration fragment = new FragmentRegistration();
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
        final View v = inflater.inflate(R.layout.fragment_registration, container, false);
        name = (EditText) v.findViewById(R.id.edittext1);
        email = (EditText) v.findViewById(R.id.edittext2);
        pass = (EditText) v.findViewById(R.id.edittext3);
        submit = (Button) v.findViewById(R.id.button1);
        final APICall apiCall = new APICall(v.getContext());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toasty.success(v.getContext(),name.getText().toString(), Toast.LENGTH_LONG,false).show();
                String username = name.getText().toString();
                String e_mail = email.getText().toString();
                String pasWord = pass.getText().toString();
                if (username.isEmpty() || e_mail.isEmpty() || pasWord.isEmpty())
                    Toasty.error(v.getContext(), "!!Some fields are empty!!", Toast.LENGTH_LONG, false).show();
                else {

                    apiCall.userRegister(username, e_mail, pasWord);
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });


        return v;
    }

}
