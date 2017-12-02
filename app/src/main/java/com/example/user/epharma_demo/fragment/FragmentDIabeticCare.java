package com.example.user.epharma_demo.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.epharma_demo.R;


public class FragmentDIabeticCare extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String submenu;
    private TextView tv;
    //private OnFragmentInteractionListener mListener;

    public FragmentDIabeticCare() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentDIabeticCare(String submenu) {
        this.submenu = submenu;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDIabeticCare.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDIabeticCare newInstance(String param1, String param2) {
        FragmentDIabeticCare fragment = new FragmentDIabeticCare();
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
        View v = inflater.inflate(R.layout.fragment_diabetic, container, false);
        tv = (TextView) v.findViewById(R.id.textview1);
        tv.setText("You Have Selected " + submenu);
        tv.setTextColor(Color.MAGENTA);
        // Inflate the layout for this fragment
        return v;
    }

}
