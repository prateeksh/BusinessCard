package com.example.prateek.businesscard.Database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Prateek on 23-12-2016.
 */

public class DataContract{

    public static final String CONTENT_AUTHORITY = "com.example.prateek.businesscard";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String BUSINESS_CARD = "cardinfo12";

    public static final class DataEntry implements BaseColumns{
        public static final String TABLE_NAME = "cardinfo12";
        public static final String DATA_ID = "data_id";
        public static final String NAME = "name";
        public static final String IMAGE = "image";
        public static final String OCCUPATION = "occupation";
        public static final String COMPANY = "company";
        public static final String PHONE = "phone";
        public static final String WORK = "work";
        public static final String HANGOUT = "hangout";
        public static final String SKYPE = "skype";
        public static final String GOOGLE = "google";
        public static final String FACEBOOK = "facebook";
        public static final String BLOG = "blog";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(BUSINESS_CARD).build();

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        BUSINESS_CARD;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +
                        BUSINESS_CARD;

        public static long getDataIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }


        public static Uri buildDataUriWithId(long id) {
            return CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        }

        public static Uri buildDataUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
    }
}
