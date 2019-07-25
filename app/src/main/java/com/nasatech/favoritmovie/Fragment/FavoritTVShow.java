package com.nasatech.favoritmovie.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nasatech.favoritmovie.Adapter.FavoriteTVShowAdapter;
import com.nasatech.favoritmovie.Entity.Favorite;
import com.nasatech.favoritmovie.FavoriteDetail;
import com.nasatech.favoritmovie.R;

import java.util.ArrayList;

import static com.nasatech.favoritmovie.helper.MappingHelper.mapCursorToArrayList;
import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class FavoritTVShow extends Fragment {
    static final int TYPE_TVSHOW = 2;
    private ArrayList<Favorite> FavoritTVShow = new ArrayList<>();
    private RecyclerView rvCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorit_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCategory = view.findViewById(R.id.movie_list);
        rvCategory.setHasFixedSize(true);

        if (savedInstanceState == null) {
            Cursor result = getContext().getContentResolver().query(CONTENT_URI, null, String.valueOf(TYPE_TVSHOW), null, null);
            FavoritTVShow = mapCursorToArrayList(result);
        } else {
            FavoritTVShow = savedInstanceState.getParcelableArrayList("favoritetvshow");
        }

        showRecyclerList();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("favoritetvshow", FavoritTVShow);
        super.onSaveInstanceState(outState);
    }

    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        FavoriteTVShowAdapter tvShowAdapter = new FavoriteTVShowAdapter(getActivity());
        tvShowAdapter.setFavoritTVShow(FavoritTVShow);
        rvCategory.setAdapter(tvShowAdapter);

        tvShowAdapter.setOnItemClickCallback(new FavoriteTVShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Favorite data) {
                showSelectedMovie(data);
            }
        });
    }

    private void showSelectedMovie(Favorite movie) {
        Intent moveWithObjectIntent = new Intent(getActivity(), FavoriteDetail.class);
        moveWithObjectIntent.putExtra("favorite", movie);
        startActivity(moveWithObjectIntent);
    }
}
