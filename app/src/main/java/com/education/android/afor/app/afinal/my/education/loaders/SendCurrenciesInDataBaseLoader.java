package com.education.android.afor.app.afinal.my.education.loaders;


import android.content.AsyncTaskLoader;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Container.CurrencyContainer;
import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme.ConverterTable.Columns;


public class SendCurrenciesInDataBaseLoader extends CursorLoader {
    private CurrencyContainer mCurrencyContainer;
    private Uri mUri;


    public SendCurrenciesInDataBaseLoader(Context context) {
        super(context);

    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Cursor loadInBackground() {

        return null;
    }

}
