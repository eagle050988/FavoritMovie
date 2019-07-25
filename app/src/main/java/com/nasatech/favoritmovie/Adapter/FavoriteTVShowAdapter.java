package com.nasatech.favoritmovie.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasatech.favoritmovie.Entity.Favorite;
import com.nasatech.favoritmovie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class FavoriteTVShowAdapter extends RecyclerView.Adapter<FavoriteTVShowAdapter.ViewHolder> {
    private ArrayList<Favorite> FavoritTVShow;
    private Context context;
    private FavoriteTVShowAdapter.OnItemClickCallback onItemClickCallback;

    public FavoriteTVShowAdapter(Context context) {
        this.context = context;
        FavoritTVShow = new ArrayList<>();
    }

    public void setFavoritTVShow(ArrayList<Favorite> favoritTVShow) {
        FavoritTVShow = favoritTVShow;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favorite_tvshow, viewGroup, false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.onBind(FavoritTVShow.get(i));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(FavoritTVShow.get(viewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return FavoritTVShow.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Favorite data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDateRelease, tvVote;
        private ImageView ivPoster, ivBackdrop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.item_title);
            tvDateRelease = itemView.findViewById(R.id.item_date_release);
            tvVote = itemView.findViewById(R.id.item_vote);

            ivPoster = itemView.findViewById(R.id.item_poster);
            ivBackdrop = itemView.findViewById(R.id.item_backdrop);
        }

        void onBind(Favorite item) {
            if (item.getImage() != null) {
                Picasso.get().load(item.getImage()).transform(new CropCircleTransformation()).into(ivPoster);
            }

            if (item.getImage() != null) {
                Picasso.get().load(item.getImage()).transform(new BlurTransformation(itemView.getContext(), 20)).into(ivBackdrop);
            }

            String title = checkTextIfNull(item.getTitle());
            if (title.length() > 30) {
                tvTitle.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                tvTitle.setText(checkTextIfNull(item.getTitle()));
            }

            tvDateRelease.setText(checkTextIfNull(item.getDate()));
            tvVote.setText(checkTextIfNull("" + item.getVote_Average()));
        }

        String checkTextIfNull(String text) {
            if (text != null && !text.isEmpty()) {
                return text;
            } else {
                return "-";
            }
        }
    }
}
