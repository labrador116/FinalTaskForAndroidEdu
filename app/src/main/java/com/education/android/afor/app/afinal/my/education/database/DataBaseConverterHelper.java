package com.education.android.afor.app.afinal.my.education.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.education.android.afor.app.afinal.my.education.database.DataBaseConverterScheme.ConverterTable;


public class DataBaseConverterHelper extends SQLiteOpenHelper {

    public static final String NAME = "converter.db";
    public static final int VERSION = 1;

    public DataBaseConverterHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ ConverterTable.NAME+
                "("+
                " _id integer primary key autoincrement, "+
                ConverterTable.Columns.CURRENCY_NAME + ", "+
                ConverterTable.Columns.CURRENCY_CHARCODE+ ", " +
                ConverterTable.Columns.NOMINAL+ ", " +
                ConverterTable.Columns.VALUE+
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
