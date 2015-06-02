package com.example.kevin.bitdate;

/**
 * Created by kevin on 6/1/15.
 */
public class User {

    private String mFirstName;
    private String pictureURL;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String name) {
        mFirstName = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
