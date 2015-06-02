package com.example.kevin.bitdate;

import android.content.Context;

import com.parse.ParseUser;

/**
 * Created by kevin on 6/1/15.
 */
public class UserDataSource {

    private static User sCurrentUser;
    private static final String COLUMN_FIRST_NAME = "firstName";
    private static final String COLUMN_PICTURE_URL = "pictureURL";

    private Context mContext;

    UserDataSource(Context context){
        mContext = context;
    }

    public static User getCurrentUser(){
        if (sCurrentUser == null && ParseUser.getCurrentUser() != null){
            sCurrentUser = new User();
            sCurrentUser.setFirstName(ParseUser.getCurrentUser().getString(COLUMN_FIRST_NAME));
            sCurrentUser.setPictureURL(ParseUser.getCurrentUser().getString(COLUMN_PICTURE_URL));
        }
        return sCurrentUser;
    }
}
