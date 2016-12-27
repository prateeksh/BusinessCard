package com.example.prateek.businesscard.Database;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Prateek on 23-12-2016.
 */

public class DataProvider extends ContentProvider {

    static final int DATA = 100;
    static final int DATA_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    //private static final String sdataByIdSelection = DataContract.DataEntry
    private DataDbHelper mOpenHelper;

    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DataContract.BUSINESS_CARD, DATA);
        matcher.addURI(authority, DataContract.BUSINESS_CARD + "/#", DATA_WITH_ID);
        return matcher;
    }

   /* private Cursor getDataById(Uri uri, String[] projection, String sortOrder){
        long id = DataContract.DataEntry.getDataIdFromUri(uri);

        return mOpenHelper.getReadableDatabase().query(
                DataContract.DataEntry.TABLE_NAME,
                projection,
                s
        )*/
    @Override
    public boolean onCreate(){
        mOpenHelper = new DataDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri){
        final int match = sUriMatcher.match(uri);

        switch (match){
            case DATA: {
                return DataContract.DataEntry.CONTENT_DIR_TYPE;
            }
            case DATA_WITH_ID: {
                return DataContract.DataEntry.CONTENT_ITEM_TYPE;
            }
            default:{
                throw new UnsupportedOperationException("unknown uri: " + uri);
            }
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){

        Cursor retCursor;
        switch (sUriMatcher.match(uri)){
            case DATA:{
                retCursor = mOpenHelper.getReadableDatabase().query(
                        DataContract.DataEntry.TABLE_NAME,
                        projection,selection,selectionArgs,
                        null,null,sortOrder
                );
                break;
            }
            /*case DATA_WITH_ID: {
                retCursor =
            }*/
            default:{
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
            }
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        final int match = sUriMatcher.match(uri);
        switch (match){
            case DATA:{
                long id = db.insert(DataContract.DataEntry.TABLE_NAME, null, values);
                if(id > 0){
                    returnUri = DataContract.DataEntry.buildDataUri(id);
                }else {
                    throw new SQLException("Failed to insert row into: " + uri);
                }
                break;
            }
            default:{
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numDeleted;
        if (null == selection) {
            selection = "1";
        }
        switch (match) {
            case DATA: {
                numDeleted = db.delete(
                        DataContract.DataEntry.TABLE_NAME, selection, selectionArgs
                );
                db.execSQL("DELETE FROM SQLITE SEQUENCE WHERE NAME = '" +
                        DataContract.DataEntry.TABLE_NAME + "'");
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        if (numDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int numUpdated;
        int match = sUriMatcher.match(uri);

        if(contentValues == null){
            throw new IllegalArgumentException("cannot have null values");
        }
        switch (match){
            case DATA:{
                numUpdated = db.update(DataContract.DataEntry.TABLE_NAME,
                        contentValues,
                        selection,
                        selectionArgs);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown Uri : " + uri);
            }
        }
        if (numUpdated > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return numUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        switch (match){
            case DATA:{
                db.beginTransaction();
                int returnCount = 0;
                try{
                    for (ContentValues value: values){
                        long id = db.insert(DataContract.DataEntry.TABLE_NAME, null, value);
                        if(id != -1){
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri,null);
                return returnCount;
            }
            default:
                return super.bulkInsert(uri,values);
        }
    }

    @Override
    @TargetApi(11)
    public void shutdown(){
        mOpenHelper.close();
        super.shutdown();
    }
}
