package com.example.kevin.bitdate;

import java.io.Serializable;

/**
 * Created by kevin on 6/1/15.
 */
public class User implements Serializable{

    private String mId;
    private String mUsername;
    private String mFirstName;
    private String pictureURL;
    private String mFacebookId;

    public String getLargePictureURL(){
        return "https://graph.facebook.com/v2.3/"+mFacebookId+"/picture?type=large";
    }

    public String getFacebookId() {
        return mFacebookId;
    }

    public void setFacebookId(String facebookId) {
        mFacebookId = facebookId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

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
