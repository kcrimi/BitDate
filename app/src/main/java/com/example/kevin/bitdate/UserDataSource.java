package com.example.kevin.bitdate;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 6/1/15.
 */
public class UserDataSource {

    private static User sCurrentUser;
    private static final String COLUMN_ID = "objectId";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_FIRST_NAME = "firstName";
    private static final String COLUMN_PICTURE_URL = "pictureURL";

    private Context mContext;

    UserDataSource(Context context){
        mContext = context;
    }

    public static User getCurrentUser(){

        if (sCurrentUser == null && ParseUser.getCurrentUser() != null){
            sCurrentUser = parseUserToUser(ParseUser.getCurrentUser());
        }
        return sCurrentUser;
    }

    public static void getUnseenUsers(final UserDataCallbacks callback){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("objectId", getCurrentUser().getId());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null){
                    List<User> users = new ArrayList<User>();
                    for (ParseUser parseUser : list){
                        User user = parseUserToUser(parseUser);
                        users.add(user);
                    }
                    if (callback != null){
                        callback.onUsersFetched(users);
                    }
                }
            }
        });
    }

    private static User parseUserToUser(ParseUser parseUser){
        User user = new User();
        user.setId(parseUser.getString(COLUMN_ID));
        user.setFirstName(parseUser.getString(COLUMN_FIRST_NAME));
        user.setPictureURL(parseUser.getString(COLUMN_PICTURE_URL));
        user.setUsername(parseUser.getString(COLUMN_USERNAME));
        return user;
    }

    public interface UserDataCallbacks{
        public void onUsersFetched(List<User> users);
    }
}
