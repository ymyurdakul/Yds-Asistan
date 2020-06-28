package com.osym.mustafa.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asp.fliptimerviewlibrary.CountDownClock;


public class ZamanFragment extends androidx.fragment.app.Fragment {


    CountDownClock clock;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_zaman, container, false);
       return view;
    }


}
