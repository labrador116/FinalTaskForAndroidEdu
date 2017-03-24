package com.education.android.afor.app.afinal.my.education.CurrencyModel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public class Currency {

    @Element (name = "Name")
    private String mCurrencyName;

    @Element (name = "CharCode")
    private String mCurrencyCharCode;

    @Element(name = "Nominal")
    private int mNominal;

    @Element(name = "Value")
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
