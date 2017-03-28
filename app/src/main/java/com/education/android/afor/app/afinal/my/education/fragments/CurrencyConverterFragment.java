package com.education.android.afor.app.afinal.my.education.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Container.CurrencyContainer;
import com.education.android.afor.app.afinal.my.education.CurrencyModel.Currency;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme;
import com.education.android.afor.app.afinal.my.education.loaders.DownloadCurrencyLoader;
import com.education.android.afor.app.afinal.my.education.services.MathConvertService;
import com.education.android.afor.app.afinal.my.education.services.QueryServices;
import com.education.android.afor.app.afinal.my.finalappforandroideducatiom.R;

import java.util.ArrayList;
import java.util.List;


public class CurrencyConverterFragment extends Fragment implements LoaderManager.LoaderCallbacks<CurrencyContainer>{

    public static final String URI_ADDRESS = "content://com.education.android.afor.app.afinal.my.education.database.provider/" + DataBaseConverterScheme.ConverterTable.NAME;

    private CurrencyContainer mCurrencyContainer;
    private Uri mUri;
    private List<String> mCharCodeItemForAdapter;
    private Spinner mFirstSpinner;
    private Spinner mSecondSpinner;
    private EditText mFirstEditText;
    private EditText mSecondEditText;
    private Button mGetConvertButton;
    private QueryServices mServices;
    private String mSelectedFirstItem;
    private String mSelectedSecondItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mServices= new QueryServices(getContext());

        if (isOnline()==true) {
            getLoaderManager().initLoader(1, null, this);
        }else{
            mCurrencyContainer = mServices.getCurrencies();
        }

        mUri = Uri.parse(URI_ADDRESS);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_converter_fragment,container,false);

        mFirstSpinner = (Spinner) view.findViewById(R.id.first_choice_currency_spinner);
        mSecondSpinner = (Spinner) view.findViewById(R.id.second_choice_currency_spinner);
        mFirstEditText = (EditText) view.findViewById(R.id.first_sum_currency_editText);
        mSecondEditText = (EditText) view. findViewById(R.id.second_sum_currency_editText);
        mGetConvertButton = (Button) view.findViewById(R.id.get_convert_sum_button);


      if(isOnline()==false) {
           mCharCodeItemForAdapter= new ArrayList<>();

            for (Currency currency : mCurrencyContainer.getCurrency()) {
                mCharCodeItemForAdapter.add(currency.getCurrencyCharCode());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mCharCodeItemForAdapter);

            mFirstSpinner.setAdapter(adapter);
            mSecondSpinner.setAdapter(adapter);
      }

      mFirstSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mSelectedFirstItem =mCharCodeItemForAdapter.get(position);
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });

        mSecondSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedSecondItem = mCharCodeItemForAdapter.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mGetConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mSelectedFirstItem.isEmpty() && !mSelectedSecondItem.isEmpty()
                        && !mFirstEditText.getText().toString().isEmpty()){

                    double value = Double.valueOf(mFirstEditText.getText().toString());

                    String valueAfterConvert = MathConvertService.ConvertCurrency(mServices,mSelectedFirstItem,value, mSelectedSecondItem);
                    mSecondEditText.setText(valueAfterConvert);
                }
            }
        });

        return view;
    }

    @Override
    public Loader<CurrencyContainer> onCreateLoader(int id, Bundle args) {
        return new DownloadCurrencyLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<CurrencyContainer> loader, CurrencyContainer data) {
        mCurrencyContainer = data;

        boolean tableExist = mServices.isTableExists(DataBaseConverterScheme.ConverterTable.NAME);

            for (Currency currency : mCurrencyContainer.getCurrency()) {
                ContentValues value = getContentValues(currency);

                if(tableExist==false) {
                    getContext().getContentResolver().insert(mUri, value);
                }else{
                    mUri = Uri.parse(URI_ADDRESS+"/"+currency.getCurrencyCharCode());
                    getContext().getContentResolver().update(mUri, value, DataBaseConverterScheme.ConverterTable.Columns.CURRENCY_CHARCODE,new String[]{currency.getCurrencyCharCode()});
                }
            }

            mCharCodeItemForAdapter= new ArrayList<>();

        for (Currency currency : mCurrencyContainer.getCurrency()) {
            mCharCodeItemForAdapter.add(currency.getCurrencyCharCode());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, mCharCodeItemForAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFirstSpinner.setAdapter(adapter);
        mSecondSpinner.setAdapter(adapter);
        Toast.makeText(getContext(), "Данные обновлены!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<CurrencyContainer> loader) {

    }


    private static ContentValues getContentValues (Currency currency){
        ContentValues values = new ContentValues();

        values.put(DataBaseConverterScheme.ConverterTable.Columns.CURRENCY_NAME, currency.getCurrencyName());
        values.put(DataBaseConverterScheme.ConverterTable.Columns.CURRENCY_CHARCODE, currency.getCurrencyCharCode());
        values.put(DataBaseConverterScheme.ConverterTable.Columns.NOMINAL, currency.getNominal());
        values.put(DataBaseConverterScheme.ConverterTable.Columns.VALUE, currency.getValue());
        return values;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
