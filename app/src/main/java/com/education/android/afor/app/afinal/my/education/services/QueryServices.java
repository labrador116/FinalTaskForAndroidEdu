package com.education.android.afor.app.afinal.my.education.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Container.CurrencyContainer;
import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterHelper;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme.ConverterTable.Columns;
import com.education.android.afor.app.afinal.my.education.database.wrapper.DataBaseConverterCursorWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.education.android.afor.app.afinal.my.education.fragments.CurrencyConverterFragment.URI_ADDRESS;

public class QueryServices {
    private Context mContext;

    public QueryServices(Context context) {
        mContext=context;
    }

    public CurrencyContainer getCurrencies(){
        CurrencyContainer container = new CurrencyContainer();

        DataBaseConverterCursorWrapper cursor=queryCurrencies();
        List<Currency> currencies = new ArrayList<>();

        try {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                currencies.add(cursor.getCurrency());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        container.setCurrency(currencies);

        return container;
    }

    public boolean isTableExists( String tableName)
    {
       SQLiteDatabase db = new DataBaseConverterHelper(mContext).getReadableDatabase();

        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }

        Cursor cursor = db.query(DataBaseConverterScheme.ConverterTable.NAME, null, null, null, null, null, null);
        int count = cursor.getCount();


        cursor.close();
        db.close();
        return count > 0;
    }

    private DataBaseConverterCursorWrapper queryCurrencies(){
      Cursor cursor = mContext.getContentResolver().query(Uri.parse(URI_ADDRESS),null,null,null,null);

        return new DataBaseConverterCursorWrapper(cursor);
    }

    public Currency getCurrency (String charCode){
        DataBaseConverterCursorWrapper cursor = queryCurrency(charCode);
        Currency currency = new Currency();
        try {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                currency = cursor.getCurrency();
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return currency;
    }

    private DataBaseConverterCursorWrapper queryCurrency(String charCode){
        Cursor cursor = mContext.getContentResolver().query(Uri.parse(URI_ADDRESS+"/"+charCode),null, Columns.CURRENCY_CHARCODE,new String[]{charCode},null);
        return new DataBaseConverterCursorWrapper(cursor);
    }
}
