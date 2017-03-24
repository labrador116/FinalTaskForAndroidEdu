package com.education.android.afor.app.afinal.my.education.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;

import java.util.ArrayList;


public class CurrencyOperationLoader extends AsyncTaskLoader<ArrayList<Currency>> {
    
    public CurrencyOperationLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<Currency> loadInBackground() {
        //ToDo Work with Network
        return null;
    }
}
