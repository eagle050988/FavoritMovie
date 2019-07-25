package com.nasatech.favoritmovie.Interface;

import android.database.Cursor;

public interface LoadFavoriteCallback {
    void preExecute();

    void postExecute(Cursor notes);
}
