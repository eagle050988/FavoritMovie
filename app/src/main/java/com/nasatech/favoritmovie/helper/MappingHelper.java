package com.nasatech.favoritmovie.helper;

import android.database.Cursor;

import com.nasatech.favoritmovie.Entity.Favorite;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.DATE;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.DESCRIPTION;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.IDEntity;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.IMAGE;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.TITLE;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.Type;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.VOTE_COUNT;

public class MappingHelper {
    public static ArrayList<Favorite> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<Favorite> FavoriteList = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID));
            int typeFavorite = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(Type));
            int identity = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(IDEntity));

            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DESCRIPTION));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DATE));
            String vote = notesCursor.getString(notesCursor.getColumnIndexOrThrow(VOTE_COUNT));
            String Image = notesCursor.getString(notesCursor.getColumnIndexOrThrow(IMAGE));
            FavoriteList.add(new Favorite(id, typeFavorite, identity, title, description, date, vote, Image));
        }
        return FavoriteList;
    }
}
