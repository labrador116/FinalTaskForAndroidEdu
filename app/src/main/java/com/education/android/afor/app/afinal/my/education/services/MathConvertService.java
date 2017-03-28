package com.education.android.afor.app.afinal.my.education.services;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;

public class MathConvertService {

    public static String ConvertCurrency(QueryServices service, String currentCharCode, double currentValue, String needCurrencyCharCode)
    {
        Currency currentCurrency = service.getCurrency(currentCharCode);
        Currency needCurrency = service.getCurrency(needCurrencyCharCode);

        double rubles = ConvertToRUB(currentCurrency,currentValue);

        String convertValue = String.valueOf(
                rubles/ (Double.valueOf(needCurrency.getValue()) / needCurrency.getNominal())
        );


        return convertValue;
    }

    private static double ConvertToRUB(Currency currentCurrency, double currentValue){
        int nominal = currentCurrency.getNominal();

        double rubles=currentValue*(Double.valueOf(currentCurrency.getValue())/nominal);

        return rubles;
    }
}
