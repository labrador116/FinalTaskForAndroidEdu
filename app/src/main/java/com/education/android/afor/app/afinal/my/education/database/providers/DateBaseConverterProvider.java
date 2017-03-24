package com.education.android.afor.app.afinal.my.education.database.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterHelper;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme;
import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme.ConverterTable.Columns;


public class DateBaseConverterProvider extends ContentProvider {

    public static final UriMatcher sMatcher;
    public static final String  AUTHORITY = "com.education.android.afor.app.afinal.my.education.database.provider";
    private SQLiteDatabase mDatabase;

    static{
        sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMatcher.addURI(AUTHORITY, DataBaseConverterScheme.ConverterTable.NAME, 1);
        sMatcher.addURI(AUTHORITY, DataBaseConverterScheme.ConverterTable.NAME+"/#", 2);
    }

    @Override
    public boolean onCreate() {
        mDatabase = new DataBaseConverterHelper(getContext()).getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch(sMatcher.match(uri)){
            case 1:
                selection = null;
                selectionArgs = null;
                break;
            case 2:
                selection = Columns.CURRENCY_CHARCODE+" =?";
                selectionArgs= new String[]{uri.getLastPathSegment()};
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        Cursor cursor = mDatabase.query(
                DataBaseConverterScheme.ConverterTable.NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (sMatcher.match(uri)){
            case 1:
                mDatabase.insert(DataBaseConverterScheme.ConverterTable.NAME,
                        null,
                        values
                );
                break;
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (sMatcher.match(uri)){
            case 1:
                mDatabase.update(Columns.CURRENCY_CHARCODE,values,selection,selectionArgs);
                break;
            case 2:
                selection = Columns.CURRENCY_CHARCODE+" =?";
                selectionArgs= new String[]{uri.getLastPathSegment()};
                mDatabase.update(Columns.CURRENCY_CHARCODE,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }


        return 0;
    }
}
