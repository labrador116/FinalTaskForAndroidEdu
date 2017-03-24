package com.education.android.afor.app.afinal.my.education.CurrencyModel;

public class Currency {
    private String mCurrencyName;
    private String mCurrencyCharCode;
    private int mNominal;
    private double mValue;

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        mCurrencyName = currencyName;
    }

    public String getCurrencyCharCode() {
        return mCurrencyCharCode;
    }

    public void setCurrencyCharCode(String currencyCharCode) {
        mCurrencyCharCode = currencyCharCode;
    }

    public int getNominal() {
        return mNominal;
    }

    public void setNominal(int nominal) {
        mNominal = nominal;
    }

    public double getValue() {
        return mValue;
    }

    public void setValue(double value) {
        mValue = value;
    }
}
