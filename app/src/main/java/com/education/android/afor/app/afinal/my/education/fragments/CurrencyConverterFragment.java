package com.education.android.afor.app.afinal.my.education.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;
import com.education.android.afor.app.afinal.my.education.loaders.CurrencyOperationLoader;
import com.education.android.afor.app.afinal.my.finalappforandroideducatiom.R;

import java.util.ArrayList;


public class CurrencyConverterFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Currency>> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(1,null,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_converter_fragment,container,false);


        return view;
    }


    @Override
    public Loader<ArrayList<Currency>> onCreateLoader(int id, Bundle args) {
        return new CurrencyOperationLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Currency>> loader, ArrayList<Currency> data) {
        //Забираем данные
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Currency>> loader) {

    }
}
