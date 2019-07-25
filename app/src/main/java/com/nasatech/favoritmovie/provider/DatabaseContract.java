package com.nasatech.favoritmovie.provider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "com.nasatech.moviecatalogue";
    private static final String SCHEME = "content";

    private DatabaseContract() {
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static Double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }

    public static final class FavoriteColumns implements BaseColumns {
        public static String TABLE_FAVORITE = "favorite";
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORITE)
                .build();
        public static String Type = "typefavorite";
        public static String IDEntity = "identity";
        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String DATE = "date";
        public static String IMAGE = "image";
        public static String VOTE_COUNT = "vote_count";
    }
}
