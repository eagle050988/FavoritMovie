package com.nasatech.favoritmovie.Entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
    private int ID;
    private int TypeFavorite;
    private int IDEntity;
    private String Title;
    private String Description;
    private String Date;
    private String Image;
    private String Vote_Average;

    public Favorite(int ID, int TypeFavorite, int IDEntity, String Title, String Description, String Date, String vote, String Image) {
        this.ID = ID;
        this.TypeFavorite = TypeFavorite;
        this.IDEntity = IDEntity;
        this.Title = Title;
        this.Description = Description;
        this.Date = Date;
        this.Image = Image;
        this.Vote_Average = vote;
    }

    public Favorite(Cursor cursor) {
        this.ID = ID;
        this.TypeFavorite = TypeFavorite;
        this.IDEntity = IDEntity;
        this.Title = Title;
        this.Description = Description;
        this.Date = Date;
        this.Image = Image;
    }

    public Favorite() {

    }

    protected Favorite(Parcel in) {
        this.ID = in.readInt();
        this.TypeFavorite = in.readInt();
        this.IDEntity = in.readInt();
        this.Title = in.readString();
        this.Description = in.readString();
        this.Date = in.readString();
        this.Image = in.readString();
        this.Vote_Average = in.readString();
    }

    public String getVote_Average() {
        return Vote_Average;
    }

    public void setVote_Average(String vote_Average) {
        Vote_Average = vote_Average;
    }

    public int getIDEntity() {
        return IDEntity;
    }

    public void setIDEntity(int IDEntity) {
        this.IDEntity = IDEntity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getTypeFavorite() {
        return TypeFavorite;
    }

    public void setTypeFavorite(int typeFavorite) {
        TypeFavorite = typeFavorite;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeInt(this.TypeFavorite);
        dest.writeInt(this.IDEntity);
        dest.writeString(this.Title);
        dest.writeString(this.Description);
        dest.writeString(this.Date);
        dest.writeString(this.Image);
        dest.writeString(this.Vote_Average);
    }
}
