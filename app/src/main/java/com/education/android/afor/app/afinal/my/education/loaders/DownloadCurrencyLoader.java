package com.education.android.afor.app.afinal.my.education.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.education.android.afor.app.afinal.my.education.CurrencyModel.Container.CurrencyContainer;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class DownloadCurrencyLoader extends AsyncTaskLoader<CurrencyContainer> {
    HttpURLConnection mHttpURLConnection;
    Persister mSerializer;
    Reader mReader;
    CurrencyContainer mContainer;

    public DownloadCurrencyLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public CurrencyContainer loadInBackground() {
        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            mHttpURLConnection =(HttpURLConnection) url.openConnection();
            mHttpURLConnection.setReadTimeout(10000 /* milliseconds */);
            mHttpURLConnection.setConnectTimeout(15000 /* milliseconds */);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setDoInput(true);

            mHttpURLConnection.connect();


            int responseCode = mHttpURLConnection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }

            InputStream in = mHttpURLConnection.getInputStream();

            String stringXML = StringReader(in);
            mReader = new StringReader(stringXML);
            mSerializer = new Persister();
            mContainer = mSerializer.read(CurrencyContainer.class,mReader,false);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mHttpURLConnection.disconnect();
        }

        return mContainer;
    }

    private String StringReader(InputStream inputStream){
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try(Reader in = new InputStreamReader(inputStream, "windows-1251")) {
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
