package com.nasatech.favoritmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasatech.favoritmovie.Entity.Favorite;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class FavoriteDetail extends AppCompatActivity {
    Favorite favorite;
    private TextView tvTitle, tvDateRelease, tvVote, tvOverview;
    private ImageView ivPoster, ivBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_detail);
        if (savedInstanceState == null) {
            favorite = getIntent().getParcelableExtra("favorite");
        } else {
            favorite = savedInstanceState.getParcelable("favoritedetail");
        }

        tvTitle = findViewById(R.id.item_title);
        tvDateRelease = findViewById(R.id.item_date_release);

        tvVote = findViewById(R.id.item_vote);
        tvOverview = findViewById(R.id.tvoverview);

        ivPoster = findViewById(R.id.item_poster);
        ivBackdrop = findViewById(R.id.item_backdrop);

        if (favorite.getImage() != null) {
            Picasso.get().load(favorite.getImage()).into(ivPoster);
        }

        if (favorite.getImage() != null) {
            Picasso.get().load(favorite.getImage()).transform(new BlurTransformation(this, 20)).into(ivBackdrop);
        }

        String title = checkTextIfNull(favorite.getTitle());
        if (title.length() > 30) {
            tvTitle.setText(String.format("%s...", title.substring(0, 29)));
        } else {
            tvTitle.setText(checkTextIfNull(favorite.getTitle()));
        }

        tvDateRelease.setText(checkTextIfNull(favorite.getDate()));
        tvVote.setText(checkTextIfNull("" + favorite.getVote_Average()));
        tvOverview.setText(checkTextIfNull(favorite.getDescription()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("favoritedetail", favorite);
        super.onSaveInstanceState(outState);
    }

    String checkTextIfNull(String text) {
        if (text != null && !text.isEmpty()) {
            return text;
        } else {
            return "-";
        }
    }
}
