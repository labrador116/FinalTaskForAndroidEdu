package com.education.android.afor.app.afinal.my.education.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.android.afor.app.afinal.my.finalappforandroideducatiom.R;


public class CurrencyConverterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_converter_fragment,container,false);


        return view;
    }
}
