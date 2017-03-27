package com.education.android.afor.app.afinal.my.education.database.wrapper;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme.ConverterTable.Columns;

public class DataBaseConverterCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public DataBaseConverterCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Currency getCurrency (){
        String CurrencyName = getString(getColumnIndex(Columns.CURRENCY_NAME));
        String CurrencyCharCode = getString(getColumnIndex(Columns.CURRENCY_CHARCODE));
        int Nominal = getInt(getColumnIndex(Columns.NOMINAL));
        String Value = getString(getColumnIndex(Columns.VALUE));

        Currency currency = new Currency();
        currency.setCurrencyName(CurrencyName);
        currency.setCurrencyCharCode(CurrencyCharCode);
        currency.setNominal(Nominal);
        currency.setValue(Value);

        return currency;
    }

}
