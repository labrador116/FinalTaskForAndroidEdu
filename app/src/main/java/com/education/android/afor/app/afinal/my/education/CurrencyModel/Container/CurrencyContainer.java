package com.education.android.afor.app.afinal.my.education.CurrencyModel.Container;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "ValCurs")
public class CurrencyContainer {
    @ElementList(inline=true, name="Valute")
    private List<Currency> mCurrency;

    public CurrencyContainer() {
        mCurrency = new ArrayList<>();
    }

    public List<Currency> getmCurrency() {
        return mCurrency;
    }

    public void setmCurrency(List<Currency> mCurrency) {
        this.mCurrency = mCurrency;
    }
}
