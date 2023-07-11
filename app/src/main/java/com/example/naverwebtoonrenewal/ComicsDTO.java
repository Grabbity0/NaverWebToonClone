package com.example.naverwebtoonrenewal;

import android.os.Parcel;
import android.os.Parcelable;

public class ComicsDTO implements Parcelable {

    private String title;
    private String artist;
    private String thumbnail;
    private String summary;

    private String summary_short;
    private String[] genre;
    private String[] weekday;

    public ComicsDTO(String title, String artist, String thumbnail, String summary, String summary_short, String[] genre, String[] weekday) {
        this.title = title;
        this.artist = artist;
        this.thumbnail = thumbnail;
        this.summary = summary;
        this.summary_short = summary_short;
        this.genre = genre;
        this.weekday = weekday;
    }

    protected ComicsDTO(Parcel in) {
        title = in.readString();
        artist = in.readString();
        thumbnail = in.readString();
        summary = in.readString();
        summary_short = in.readString();
        genre = in.createStringArray();
        weekday = in.createStringArray();
    }

    public static final Creator<ComicsDTO> CREATOR = new Creator<ComicsDTO>() {
        @Override
        public ComicsDTO createFromParcel(Parcel in) {
            return new ComicsDTO(in);
        }

        @Override
        public ComicsDTO[] newArray(int size) {
            return new ComicsDTO[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary_short() {
        return summary_short;
    }

    public void setSummary_short(String summary_short) {
        this.summary_short = summary_short;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String[] getWeekday() {
        return weekday;
    }

    public void setWeekday(String[] weekday) {
        this.weekday = weekday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(thumbnail);
        dest.writeString(summary);
        dest.writeString(summary_short);
        dest.writeStringArray(genre);
        dest.writeStringArray(weekday);
    }
}
