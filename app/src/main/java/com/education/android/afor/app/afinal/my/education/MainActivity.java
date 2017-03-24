package com.education.android.afor.app.afinal.my.education;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.education.android.afor.app.afinal.my.finalappforandroideducatiom.R;
import com.education.android.afor.app.afinal.my.education.fragments.CurrencyConverterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportFragmentManager().findFragmentById(R.id.container_for_currency_converter_fragment)==null){
            CurrencyConverterFragment fragment = new CurrencyConverterFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container_for_currency_converter_fragment,fragment).commit();
        }
    }
}

//ToDo Connection

