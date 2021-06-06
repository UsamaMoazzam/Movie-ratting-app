
package com.example.project;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class show implements Parcelable {
    String name,genre,description,rating;
    String time,date;
    String img;
    String cast;

    protected show(Parcel in) {
        name = in.readString();
        img=in.readString();
        cast=in.readString();
        genre = in.readString();
        description = in.readString();
        rating = in.readString();
        time = in.readString();
        date = in.readString();
    }

    public static final Creator<show> CREATOR = new Creator<show>() {
        @Override
        public show createFromParcel(Parcel in) {
            return new show(in);
        }

        @Override
        public show[] newArray(int size) {
            return new show[size];
        }
    };

    public show(String name, String genre, String description, String rating, String time, String date, String img, String cast) {
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.time = time;
        this.date = date;
        this.img = img;
        this.cast = cast;
    }

    public static Creator<show> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(genre);
        dest.writeString(cast);
        dest.writeString(description);
        dest.writeString(rating);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(img);

    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
