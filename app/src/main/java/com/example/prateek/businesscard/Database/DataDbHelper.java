package com.example.prateek.businesscard.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Prateek on 23-12-2016.
 */

public class DataDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "businesscardinfo1.db";

    private static final int DATABASE_VERSION = 13;

    public DataDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_TABLE = "CREATE TABLE " +
                DataContract.DataEntry.TABLE_NAME + "(" +
                DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                DataContract.DataEntry.NAME + " TEXT NOT NULL, " +

                DataContract.DataEntry.OCCUPATION + " TEXT NOT NULL, " +
                DataContract.DataEntry.COMPANY + " TEXT NOT NULL, " +
                DataContract.DataEntry.PHONE + " INTEGER NOT NULL, " +
                DataContract.DataEntry.WORK + " INTEGER NOT NULL, " +
                DataContract.DataEntry.HANGOUT + " TEXT NOT NULL, " +
                DataContract.DataEntry.SKYPE + " TEXT NOT NULL, " +
                DataContract.DataEntry.GOOGLE + " TEXT NOT NULL, " +
                DataContract.DataEntry.FACEBOOK + " TEXT NOT NULL, " +
                DataContract.DataEntry.BLOG + " TEXT NOT NULL" + ");";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataContract.DataEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
